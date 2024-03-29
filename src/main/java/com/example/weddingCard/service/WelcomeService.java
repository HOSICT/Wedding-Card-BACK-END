package com.example.weddingCard.service;

import com.example.weddingCard.dto.WelcomeDTO;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.entity.Welcome;
import com.example.weddingCard.repository.WelcomeRepository;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WelcomeService {

    @Autowired
    private WelcomeRepository welcomeRepository;

    @Transactional
    public void saveWelcome(WelcomeDTO welcomeDTO, Information information) {
        Welcome welcome = dtoWelcomeEntity(welcomeDTO, information);
        welcomeRepository.save(welcome);
    }

    private Welcome dtoWelcomeEntity(WelcomeDTO welcomeDTO, Information information) {
        List<Welcome> findWeddingIdWelcome = welcomeRepository.findByWeddingId(information);
        Welcome welcome;
        if (findWeddingIdWelcome.isEmpty()) {
            welcome = new Welcome();
        } else {
            welcome = findWeddingIdWelcome.get(0);
        }

        welcome.setWeddingId(information);
        welcome.setWelcomeMessage(welcomeDTO.getWelcomeMessage());

        return welcome;
    }

    public List<Welcome> findWelcomeByWeddingId(List<Information> information) {
        return welcomeRepository.findByWeddingIdIn(information);
    }
}
