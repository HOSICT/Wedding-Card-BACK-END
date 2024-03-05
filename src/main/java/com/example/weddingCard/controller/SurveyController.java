package com.example.weddingCard.controller;

import com.example.weddingCard.dto.SurveyDto;
import com.example.weddingCard.entity.WecaUser;
import com.example.weddingCard.service.SurveyService;
import com.example.weddingCard.service.UserIdService;
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
    private final UserIdService userIdSerivce;

    @Autowired
    public SurveyController(SurveyService surveyService, UserIdService userIdSerivce) {
        this.surveyService = surveyService;
        this.userIdSerivce = userIdSerivce;
    }

    @PostMapping("/survey")
    public ResponseEntity<?> saveSurvey(@RequestBody SurveyDto surveyDTO, @RequestHeader("Uid") String userId) {
        WecaUser user = userIdSerivce.saveOrUpdateUserId(userId);
        surveyService.saveSurvey(surveyDTO, user);

        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.OK.value());
        response.put("msg", "ok");

        return ResponseEntity.ok(response);
    }
}
