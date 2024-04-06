package com.example.weddingCard.controller;

import com.example.weddingCard.dto.InformationDTO;
import com.example.weddingCard.entity.WecaUser;
import com.example.weddingCard.response.WecaResponse;
import com.example.weddingCard.service.InformationService;
import com.example.weddingCard.service.UserIdService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class TemplateController {

    private final ObjectMapper objectMapper;
    private final InformationService informationService;
    private final UserIdService userIdService;

    public TemplateController(ObjectMapper objectMapper, InformationService informationService, UserIdService userIdService) {
        this.objectMapper = objectMapper;
        this.informationService = informationService;
        this.userIdService = userIdService;
    }

    @PostMapping("save/template")
    public ResponseEntity<WecaResponse> uploadTemplate(@RequestHeader("uid") String userId,
                                                       @RequestBody String templateRequest) {
        WecaResponse response;
        WecaUser user = userIdService.findUserByUid(userId);

        try {
            InformationDTO informationDTO = objectMapper.readValue(templateRequest, InformationDTO.class);
            informationService.saveTemplateId(informationDTO, user);
        } catch (IOException e) {
            response = new WecaResponse(500, "TemplateId upload failed: ");
            return ResponseEntity.internalServerError().body(response);
        }

        response = new WecaResponse(200, "ok");
        return ResponseEntity.ok(response);
    }
}
