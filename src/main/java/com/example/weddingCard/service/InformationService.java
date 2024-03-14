package com.example.weddingCard.service;

import com.example.weddingCard.dto.InformationDTO;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.entity.WecaUser;
import com.example.weddingCard.enums.Side;
import com.example.weddingCard.repository.InformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class InformationService {

    private final AccountsService accountsService;
    private final RoadService roadService;
    private final ManagementService managementService;

    @Autowired
    public InformationRepository informationRepository;

    @Autowired
    public InformationService(AccountsService accountsService, RoadService roadService, ManagementService managementService) {
        this.accountsService = accountsService;
        this.roadService = roadService;
        this.managementService = managementService;
    }

    @Transactional
    public Information saveInformation(InformationDTO informationDTO, WecaUser user){
        Information information = dtoInformationEntity(informationDTO, user);
        information = informationRepository.save(information);

        accountsService.saveAccounts(informationDTO.getHusband(), information, Side.HUSBAND);
        accountsService.saveAccounts(informationDTO.getWife(), information, Side.WIFE);

        roadService.saveRoad(informationDTO, information);
        managementService.saveManagement(informationDTO, information);

        return information;
    }

    private Information dtoInformationEntity(InformationDTO informationDTO, WecaUser user){
        List<Information> findUserInformation = informationRepository.findByUser(user);
        Information information;
        if (findUserInformation.isEmpty()) {
            information = new Information();
        } else {
            information = findUserInformation.get(0);
        }

        information.setUser(user);
        information.setDate(adjustDate(informationDTO.getDate()));
        information.setAddress(informationDTO.getAddress());
        information.setWeddingHall(informationDTO.getWeddingHall());
        information.setWelcome(informationDTO.getWelcome());
        return information;
    }

    private LocalDateTime adjustDate(LocalDateTime localDateTime) {
        String getDateToString = localDateTime.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(getDateToString, formatter);
        LocalDateTime adjustedDate = dateTime.withSecond(0).withNano(0);

        return adjustedDate;
    }
}
