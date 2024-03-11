package com.example.weddingCard.controller;

import com.example.weddingCard.dto.SurveyDto;
import com.example.weddingCard.entity.WecaUser;
import com.example.weddingCard.response.WecaResponse;
import com.example.weddingCard.service.SurveyService;
import com.example.weddingCard.service.UserIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/save")
public class SurveyController {

    private final SurveyService surveyService;
    private final UserIdService userIdService;

    @Autowired
    public SurveyController(SurveyService surveyService, UserIdService userIdService) {
        this.surveyService = surveyService;
        this.userIdService = userIdService;
    }

    @PostMapping("/survey")
    public ResponseEntity<WecaResponse> saveSurvey(@RequestBody SurveyDto surveyDTO, @RequestHeader("Uid") String userId) {
        WecaResponse response;
        WecaUser user = userIdService.saveOrUpdateUserId(userId);
        surveyService.saveSurvey(surveyDTO, user);

        response = new WecaResponse(200, "ok");
        return ResponseEntity.ok(response);
    }
}
