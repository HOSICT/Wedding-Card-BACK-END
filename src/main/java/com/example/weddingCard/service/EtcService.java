package com.example.weddingCard.service;

import com.example.weddingCard.dto.EtcDTO;
import com.example.weddingCard.dto.RoadDTO;
import com.example.weddingCard.entity.Etc;
import com.example.weddingCard.entity.Road;
import com.example.weddingCard.repository.EtcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtcService {

    @Autowired
    private EtcRepository etcRepository;

    public void saveEtc(RoadDTO roadDTO, Road road) {
        Etc etc = dtoEtcEntity(roadDTO.getEtc(), road);
        etcRepository.save(etc);
    }

    private Etc dtoEtcEntity(EtcDTO etcDTO, Road road) {
        List<Etc> findRoadIdEtc = etcRepository.findByRoadId(road);
        Etc etc;
        if (findRoadIdEtc.isEmpty()) {
            etc = new Etc();
        } else {
            etc = findRoadIdEtc.get(0);
        }
        etc.setRoadId(road);
        etc.setTransportType(etcDTO.getTransportType());
        etc.setInfo(etcDTO.getInfo());
        return etc;
    }
}
