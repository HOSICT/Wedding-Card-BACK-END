package com.example.weddingCard.service;

import com.example.weddingCard.dto.InformationDTO;
import com.example.weddingCard.dto.RoadDTO;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.entity.Road;
import com.example.weddingCard.repository.RoadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoadService {

    @Autowired
    private RoadRepository roadRepository;
    public void saveRoad(InformationDTO informationDTO, Information information) {
        Road road = dtoRoadEntity(informationDTO.getRoad(), information);
        roadRepository.save(road);
    }

    private Road dtoRoadEntity(RoadDTO roadDTO, Information information) {
        List<Road> findWeddingIdRoad = roadRepository.findByWeddingId(information);
        Road road;
        if (findWeddingIdRoad.isEmpty()) {
            road = new Road();
        } else {
            road = findWeddingIdRoad.get(0);
        }
        road.setWeddingId(information);
        road.setSubway(roadDTO.getSubway());
        road.setBus(roadDTO.getBus());
        road.setCar(roadDTO.getCar());
        road.setRentBus(roadDTO.getRentBus() != null ? roadDTO.getRentBus() : "");
        road.setEtc(roadDTO.getEtc() != null ? roadDTO.getEtc() : "");
        return road;
    }
}
