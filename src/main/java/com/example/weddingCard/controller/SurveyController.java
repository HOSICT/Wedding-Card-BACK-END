package com.example.weddingCard.controller;

import com.example.weddingCard.dto.SurveyDTO;
import com.example.weddingCard.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/save")
public class SurveyController {

    private final SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping("/survey")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSurvey(@RequestBody SurveyDTO surveyDTO, @RequestHeader("Uid") String userId) {
        surveyService.createOrUpdateSurveyAndUser(surveyDTO, userId);
    }
}
