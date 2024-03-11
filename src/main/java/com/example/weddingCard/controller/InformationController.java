package com.example.weddingCard.controller;

import com.example.weddingCard.dto.InformationDTO;
import com.example.weddingCard.entity.ImagesUrl;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.entity.WecaUser;
import com.example.weddingCard.response.WecaResponse;
import com.example.weddingCard.service.EtcService;
import com.example.weddingCard.service.InformationService;
import com.example.weddingCard.service.S3Service;
import com.example.weddingCard.service.UserIdService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

@RestController
public class InformationController {

    private final ObjectMapper objectMapper;

    private final S3Service s3Service;
    private final InformationService informationService;
    private final UserIdService userIdService;

    public InformationController(ObjectMapper objectMapper, S3Service s3Service, InformationService informationService, UserIdService userIdService, EtcService etcService) {
        this.objectMapper = objectMapper;
        this.s3Service = s3Service;
        this.informationService = informationService;
        this.userIdService = userIdService;
    }

    @PostMapping("/save/information")
    public ResponseEntity<WecaResponse> uploadFile(@RequestParam("images") MultipartFile[] files, @RequestParam("json") String jsonRequest, @RequestHeader("Uid") String userId) {
        WecaResponse response;
        WecaUser user = userIdService.saveOrUpdateUserId(userId);
        if (files.length > 15) {
            response = new WecaResponse(400, "Error: Cannot upload more than 15 files at a time.");
            return ResponseEntity.badRequest().body(response);
        }
        try {
            InformationDTO informationDTO = objectMapper.readValue(jsonRequest, InformationDTO.class);
            Information information = informationService.saveInformation(informationDTO, user);
            for (MultipartFile file : files) {
                s3Service.uploadFile(file);
                s3Service.saveImagesUrl(files, information);
            }
        } catch (IOException e) {
            response = new WecaResponse(500, "File upload failed" + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }

        response = new WecaResponse(200, "ok");
        return ResponseEntity.ok(response);
    }
}