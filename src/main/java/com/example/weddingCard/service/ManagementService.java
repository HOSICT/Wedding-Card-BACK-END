package com.example.weddingCard.service;

import com.example.weddingCard.dto.InformationDTO;
import com.example.weddingCard.dto.ManagementDTO;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.entity.Management;
import com.example.weddingCard.repository.ManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManagementService {

    @Autowired
    private ManagementRepository managementRepository;

    @Transactional
    public void saveManagement(InformationDTO informationDTO, Information information) {
        Management management = dtoManagementEntity(informationDTO.getManagement(), information);
        managementRepository.save(management);
    }

    private Management dtoManagementEntity(ManagementDTO managementDTO, Information information) {
        List<Management> findWeddingIdManagement = managementRepository.findByWeddingId(information);
        Management management;
        if (findWeddingIdManagement.isEmpty()) {
            management = new Management();
        } else {
            management = findWeddingIdManagement.get(0);
        }
        management.setWeddingId(information);
        management.setManagementPassword(managementDTO.getManagementPassword());
        return management;
    }
}
