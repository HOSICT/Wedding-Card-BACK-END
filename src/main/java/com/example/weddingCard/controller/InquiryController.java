package com.example.weddingCard.controller;

import com.example.weddingCard.entity.*;
import com.example.weddingCard.service.ContentsService;
import com.example.weddingCard.service.InformationService;
import com.example.weddingCard.service.LocationService;
import com.example.weddingCard.service.WelcomeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class InquiryController {

    private final InformationService informationService;
    private final LocationService locationService;
    private final WelcomeService welcomeService;
    private final ContentsService contentsService;

    public InquiryController(InformationService informationService, LocationService locationService, WelcomeService welcomeService, ContentsService contentsService) {
        this.informationService = informationService;
        this.locationService = locationService;
        this.welcomeService = welcomeService;
        this.contentsService = contentsService;
    }

    @GetMapping("/inquiry")
    public ResponseEntity<Map<String, Object>> getInformation(@RequestHeader(name = "uid") WecaUser uid) {
        List<Information> informationList = informationService.findInformationByUserId(uid);

        List<Location> locationList = locationService.findLocationByWeddingId(informationList);
        List<Welcome> welcomeList = welcomeService.findWelcomeByWeddingId(informationList);
        List<Contents> contentsList = contentsService.findContentsByWeddingId(informationList);

        List<Map<String, Object>> informationJson = informationList.stream().map(information -> {
            Map<String, Object> informationMap = new HashMap<>();
            informationMap.put("date", information.getDate());
            informationMap.put("welcome_align", information.getWelcomeAlign());
            return informationMap;
        }).toList();

        Map<String, Object> locationJson = locationList.isEmpty() ? new HashMap<>() : Map.of(
                "address", locationList.get(0).getAddress(),
                "wedding_hall", locationList.get(0).getWeddingHall(),
                "latitude", locationList.get(0).getLatitude(),
                "longitude", locationList.get(0).getLongitude()
        );

        Map<String, Object> contentsJson = contentsList.isEmpty() ? new HashMap<>() : Map.of(
                "video_id", contentsList.get(0).getVideoId(),
                "bgm", contentsList.get(0).getBgm(),
                "effect", contentsList.get(0).getEffect(),
                "live_url", contentsList.get(0).getLiveUrl()
        );

        ObjectMapper objectMapper = new ObjectMapper();
        Object welcomeJson = new ArrayList<>();

        if (!welcomeList.isEmpty()) {
            String welcomeMessage = welcomeList.get(0).getWelcomeMessage();
            try {
                welcomeJson = objectMapper.readValue(welcomeMessage, Object.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        Map<String, Object> responseBody = informationJson.isEmpty() ? new HashMap<>() : informationJson.get(0);
        responseBody.put("location", locationJson);
        responseBody.put("welcome", welcomeJson);
        responseBody.put("contents", contentsJson);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
