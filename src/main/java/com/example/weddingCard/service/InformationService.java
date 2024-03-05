package com.example.weddingCard.service;

import com.example.weddingCard.dto.InformationDTO;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.repository.InformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformationService {
    @Autowired
    public InformationRepository informationRepository;

    public void saveInformation(InformationDTO informationDTO){
        Information information = dtoInformationEntity(informationDTO);
        information = informationRepository.save(information);
    }

    private Information dtoInformationEntity(InformationDTO informationDTO){
        Information information = new Information();
        information.setTemplateId(informationDTO.getTemplateId());
        information.setDate(informationDTO.getDate());
        information.setAddress(informationDTO.getAddress());
        information.setWeddingHall(informationDTO.getWeddingHall());
        information.setWelcome(informationDTO.getWelcome());
        return information;
    }
}
