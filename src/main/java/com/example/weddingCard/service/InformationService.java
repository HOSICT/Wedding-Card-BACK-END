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
import java.time.ZoneId;
import java.util.List;

@Service
public class InformationService {

    private final AccountsService accountsService;
    private final ManagementService managementService;
    private final ContentsService contentsService;
    private final OpenGraphService openGraphService;
    private final LocationService locationService;
    private final S3Service s3Service;

    @Autowired
    public InformationRepository informationRepository;

    @Autowired
    public InformationService(AccountsService accountsService, ManagementService managementService, ContentsService contentsService, OpenGraphService openGraphService, LocationService locationService, S3Service s3Service) {
        this.accountsService = accountsService;
        this.managementService = managementService;
        this.contentsService = contentsService;
        this.openGraphService = openGraphService;
        this.locationService = locationService;
        this.s3Service = s3Service;
    }

    @Transactional
    public Information saveInformation(InformationDTO informationDTO, WecaUser user) {
        Information information = dtoInformationEntity(informationDTO, user);
        information = informationRepository.save(information);

        locationService.saveLocation(informationDTO, information);
        accountsService.saveAccounts(informationDTO.getHusband(), information, Side.HUSBAND);
        accountsService.saveAccounts(informationDTO.getWife(), information, Side.WIFE);
        managementService.saveManagement(informationDTO, information);
        contentsService.saveContents(informationDTO, information);
        openGraphService.saveOpenGraph(informationDTO, information);

        return information;
    }

    public List<Information> findInformationByUserId(WecaUser wecaUser) {
        return informationRepository.findByUser(wecaUser);
    }

    private Information dtoInformationEntity(InformationDTO informationDTO, WecaUser wecaUser){
        List<Information> findUserInformation = informationRepository.findByUser(wecaUser);
        Information information;
        if (findUserInformation.isEmpty()) {
            information = new Information();
        } else {
            information = findUserInformation.get(0);
        }

        information.setUser(wecaUser);
        information.setDate(adjustDate(informationDTO.getDate()));
        information.setWelcomeAlign(informationDTO.getWelcomeAlign());
        return information;
    }

    private Information dtoInformationTemplateEntity(InformationDTO informationDTO, WecaUser wecaUser) {
        List<Information> findUserInformation = informationRepository.findByUser(wecaUser);
        Information information = findUserInformation.get(0);

        information.setUser(wecaUser);
        information.setTemplateId(informationDTO.getTemplateId());

        return information;
    }

    private LocalDateTime adjustDate(LocalDateTime localDateTime) {
        String getDateToString = localDateTime.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(getDateToString, formatter);
        LocalDateTime adjustedDate = dateTime.withSecond(0).withNano(0);

        return adjustedDate;
    }

    @Transactional
    public Information saveTemplateId(InformationDTO informationDTO, WecaUser user) {
        Information information = informationRepository.findByUser(user).stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Information not found for user: " + user.getUserId()));
        information.setTemplateId(informationDTO.getTemplateId());
        return informationRepository.save(information);
    }

    @Transactional
    public void deleteOldInformation() {
        LocalDateTime oneDayAgo = LocalDateTime.now(ZoneId.of("Asia/Seoul")).minusDays(1);
        List<Information> oldInformation = informationRepository.findAllByDateBefore(oneDayAgo);
        for (Information info : oldInformation) {
            s3Service.deleteFilesFromS3(info);
            informationRepository.delete(info);
        }
    }

}
