package com.example.weddingCard.service;

import com.example.weddingCard.dto.BusDTO;
import com.example.weddingCard.entity.Bus;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    @Transactional
    public void saveBus(BusDTO busDTO, Information information) {
        Bus bus = dtoBusEntity(busDTO, information);
        busRepository.save(bus);
    }

    private Bus dtoBusEntity(BusDTO busDTO, Information information) {
        List<Bus> findWeddingIdBus = busRepository.findByWeddingId(information);
        Bus bus;
        if (findWeddingIdBus.isEmpty()) {
            bus = new Bus();
        } else {
            bus = findWeddingIdBus.get(0);
        }

        bus.setWeddingId(information);
        bus.setBusMessage(busDTO.getBusMessage());

        return bus;
    }
}
