package com.example.weddingCard.controller;

import com.example.weddingCard.dto.InformationDTO;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.entity.WecaUser;
import com.example.weddingCard.response.WecaResponse;
import com.example.weddingCard.service.InformationService;
import com.example.weddingCard.service.UserIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TemplateController {

    private final InformationService informationService;
    private final UserIdService userIdService;

    @Autowired
    public TemplateController(InformationService informationService, UserIdService userIdService) {
        this.informationService = informationService;
        this.userIdService = userIdService;
    }

    @PostMapping("/save/template")
    public ResponseEntity<WecaResponse> saveTemplate(@RequestHeader("Uid") String userId,
                                                     @RequestBody InformationDTO informationDTO) {
        WecaUser user = userIdService.findUserByUid(userId);
        Information information = informationService.saveTemplateId(informationDTO, user);

        WecaResponse response = new WecaResponse(200, "ok");
        return ResponseEntity.ok(response);
    }
}