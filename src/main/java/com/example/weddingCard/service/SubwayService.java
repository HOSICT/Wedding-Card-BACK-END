package com.example.weddingCard.service;

import com.example.weddingCard.dto.SubwayDTO;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.entity.Subway;
import com.example.weddingCard.repository.SubwayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubwayService {

    @Autowired
    private SubwayRepository subwayRepository;

    @Transactional
    public void saveSubway(SubwayDTO subwayDTO, Information information) {
        Subway subway = dtoSubwayEntity(subwayDTO, information);
        subwayRepository.save(subway);
    }

    private Subway dtoSubwayEntity(SubwayDTO subwayDTO, Information information) {
        List<Subway> findWeddingIdSubway = subwayRepository.findByWeddingId(information);
        Subway subway;
        if (findWeddingIdSubway.isEmpty()) {
            subway = new Subway();
        } else {
            subway = findWeddingIdSubway.get(0);
        }

        subway.setWeddingId(information);
        subway.setSubwayMessage(subwayDTO.getSubwayMessage());

        return subway;
    }
}
