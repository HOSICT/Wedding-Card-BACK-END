package com.example.weddingCard.controller;

import com.example.weddingCard.dto.InformationDTO;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public ResponseEntity<WecaResponse> uploadFile(@RequestParam("mainImage") MultipartFile mainImage,
                                                   @RequestParam(value = "images1", required = false) MultipartFile images1,
                                                   @RequestParam(value = "images2", required = false) MultipartFile images2,
                                                   @RequestParam(value = "images3", required = false) MultipartFile images3,
                                                   @RequestParam(value = "images4", required = false) MultipartFile images4,
                                                   @RequestParam(value = "images5", required = false) MultipartFile images5,
                                                   @RequestParam(value = "images6", required = false) MultipartFile images6,
                                                   @RequestParam(value = "images7", required = false) MultipartFile images7,
                                                   @RequestParam(value = "images8", required = false) MultipartFile images8,
                                                   @RequestParam(value = "images9", required = false) MultipartFile images9,
                                                   @RequestParam(value = "images10", required = false) MultipartFile images10,
                                                   @RequestParam(value = "images11", required = false) MultipartFile images11,
                                                   @RequestParam(value = "images12", required = false) MultipartFile images12,
                                                   @RequestParam(value = "images13", required = false) MultipartFile images13,
                                                   @RequestParam(value = "images14", required = false) MultipartFile images14,
                                                   @RequestParam("json") String jsonRequest,
                                                   @RequestHeader("Uid") String userId) {
        WecaResponse response;
        WecaUser user = userIdService.saveOrUpdateUserId(userId);
        List<MultipartFile> files = new ArrayList<>();

        files.add(mainImage);
        files.addAll(Arrays.asList(images1, images2, images3, images4, images5, images6, images7, images8, images9, images10, images11, images12, images13, images14));

        files = files.stream().filter(Objects::nonNull).collect(Collectors.toList());

        if (files.size() > 15) {
            response = new WecaResponse(400, "Error: Cannot upload more than 15 files at a time.");
            return ResponseEntity.badRequest().body(response);
        }
        try {
            InformationDTO informationDTO = objectMapper.readValue(jsonRequest, InformationDTO.class);
            Information information = informationService.saveInformation(informationDTO, user);

            String[] arrayMultipartFile = new String[files.size()];
            for (int i = 0; i < files.size(); i++) {
                String prefix = (i == 0) ? "mainImage" : "Images" + i;
                arrayMultipartFile[i] = s3Service.uploadFile(files.get(i), prefix);
            }
            s3Service.saveImagesUrl(arrayMultipartFile, information);
        } catch (IOException e) {
            response = new WecaResponse(500, "File upload failed" + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }

        response = new WecaResponse(200, "ok");
        return ResponseEntity.ok(response);
    }
}