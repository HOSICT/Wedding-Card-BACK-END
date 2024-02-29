package com.example.weddingCard.service;

import com.example.weddingCard.dto.SurveyDto;
import com.example.weddingCard.entity.Survey;
import com.example.weddingCard.entity.WecaUser;
import com.example.weddingCard.repository.SurveyRepository;
import com.example.weddingCard.repository.WecaUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final WecaUserRepository userRepository;

    @Autowired
    public SurveyService(WecaUserRepository userRepository, SurveyRepository surveyRepository) {
        this.userRepository = userRepository;
        this.surveyRepository = surveyRepository;
    }

    @Transactional
    public void createOrUpdateSurveyAndUser(SurveyDto surveyDTO, String userId) {
        WecaUser user = userRepository.findById(userId)
                .orElseGet(() -> {
                    WecaUser newUser = new WecaUser();
                    newUser.setUserId(userId);
                    return userRepository.save(newUser);
                });

        List<Survey> surveys = surveyRepository.findByUser(user);
        Survey survey;
        if (surveys.isEmpty()) {
            survey = new Survey();
        } else {
            survey = surveys.get(0);
        }

        survey.setUser(user);
        survey.setHasVideo(surveyDTO.getHasVideo());
        survey.setHasLive(surveyDTO.getHasLive());

        surveyRepository.save(survey);
    }
}
