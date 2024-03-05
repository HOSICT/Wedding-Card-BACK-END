package com.example.weddingCard.service;

import com.example.weddingCard.dto.SurveyDto;
import com.example.weddingCard.entity.Survey;
import com.example.weddingCard.entity.WecaUser;
import com.example.weddingCard.repository.SurveyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    @Autowired
    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    @Transactional
    public void saveSurvey(SurveyDto surveyDTO, WecaUser user) {
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
