package com.example.weddingCard.controller;

import com.example.weddingCard.dto.InformationDTO;
import com.example.weddingCard.entity.WecaUser;
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

    public InformationController(ObjectMapper objectMapper, S3Service s3Service, InformationService informationService, UserIdService userIdService) {
        this.objectMapper = objectMapper;
        this.s3Service = s3Service;
        this.informationService = informationService;
        this.userIdService = userIdService;
    }

    @PostMapping("/save/information")
    public ResponseEntity<String> uploadFile(@RequestParam("images") MultipartFile[] files, @RequestParam("json") String jsonRequest, @RequestHeader("Uid") String userId) {
        WecaUser user = userIdService.saveOrUpdateUserId(userId);
        if (files.length > 15) {
            return ResponseEntity.badRequest().body("Error: Cannot upload more than 15 files at a time.");
        }
        for (MultipartFile file : files) {
            try {
                s3Service.uploadFile(file);
                InformationDTO informationDTO = objectMapper.readValue(jsonRequest, InformationDTO.class);
                informationService.saveInformation(informationDTO, user);
            } catch (IOException e) {
                return ResponseEntity.internalServerError().body("File upload failed: " + e.getMessage());
            }
        }


        return ResponseEntity.ok("Files uploaded successfully");
    }
}