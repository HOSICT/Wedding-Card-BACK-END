package com.example.weddingCard.controller;

import com.example.weddingCard.dto.SurveyDto;
import com.example.weddingCard.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/save")
public class SurveyController {

    private final SurveyService surveyService;

    @Autowired
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @PostMapping("/survey")
    public ResponseEntity<?> saveSurvey(@RequestBody SurveyDto surveyDTO, @RequestHeader("Uid") String userId) {
        surveyService.createOrUpdateSurveyAndUser(surveyDTO, userId);

        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("msg", "ok");

        return ResponseEntity.ok(response);
    }
}
