package com.example.weddingCard.service;

import com.example.weddingCard.dto.EtcDTO;
import com.example.weddingCard.entity.Etc;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.repository.EtcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtcService {

    @Autowired
    private EtcRepository etcRepository;

    public void saveEtc(EtcDTO etcDTO, Information information) {
        Etc etc = dtoEtcEntity(etcDTO, information);
        etcRepository.save(etc);
    }

    private Etc dtoEtcEntity(EtcDTO etcDTO, Information information) {
        List<Etc> findWeddingIdEtc = etcRepository.findByWeddingId(information);
        Etc etc;
        if (findWeddingIdEtc.isEmpty()) {
            etc = new Etc();
        } else {
            etc = findWeddingIdEtc.get(0);
        }

        etc.setWeddingId(information);
        etc.setTransportType(etcDTO.getTransportType());
        etc.setInfo(etcDTO.getInfo());

        return etc;
    }
}
