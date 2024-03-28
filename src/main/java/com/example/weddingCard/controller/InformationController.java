package com.example.weddingCard.controller;

import com.example.weddingCard.dto.*;
import com.example.weddingCard.entity.Information;
import com.example.weddingCard.entity.WecaUser;
import com.example.weddingCard.response.WecaResponse;
import com.example.weddingCard.service.*;
import com.example.weddingCard.util.ParsingEditorState;
import org.json.JSONObject;
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
    private final WelcomeService welcomeService;
    private final SubwayService subwayService;
    private final BusService busService;
    private final CarService carService;
    private final EtcService etcService;
    private final ParsingEditorState parsingEditorState;

    public InformationController(ObjectMapper objectMapper, S3Service s3Service, InformationService informationService, UserIdService userIdService, WelcomeService welcomeService, SubwayService subwayService, BusService busService, CarService carService, EtcService etcService, ParsingEditorState parsingEditorState) {
        this.objectMapper = objectMapper;
        this.s3Service = s3Service;
        this.informationService = informationService;
        this.userIdService = userIdService;
        this.welcomeService = welcomeService;
        this.subwayService = subwayService;
        this.busService = busService;
        this.carService = carService;
        this.etcService = etcService;
        this.parsingEditorState = parsingEditorState;
    }

    @PostMapping("/save/information")
    public ResponseEntity<WecaResponse> uploadInformation(@RequestParam("mainImage") MultipartFile mainImage,
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
                                                   @RequestParam(value = "images15", required = false) MultipartFile images15,
                                                   @RequestParam(value = "thumbnail") MultipartFile thumbnail,
                                                   @RequestParam("json") String jsonRequest,
                                                   @RequestHeader("Uid") String userId) {
        WecaResponse response;
        WecaUser user = userIdService.saveOrUpdateUserId(userId);
        List<MultipartFile> files = new ArrayList<>();

        files.add(mainImage);
        files.add(thumbnail);
        files.addAll(Arrays.asList(images1, images2, images3, images4, images5, images6, images7, images8, images9, images10, images11, images12, images13, images14, images15));

        files = files.stream().filter(Objects::nonNull).collect(Collectors.toList());

        if (files.size() > 16) {
            response = new WecaResponse(400, "Error: Cannot upload more than 15 files at a time.");
            return ResponseEntity.badRequest().body(response);
        }
        try {
            InformationDTO informationDTO = objectMapper.readValue(jsonRequest, InformationDTO.class);
            Information information = informationService.saveInformation(informationDTO, user);

            WelcomeDTO welcomeDTO = new WelcomeDTO();
            welcomeDTO.setWelcomeMessage(parsingEditorState.parsingEditorStateToString(jsonRequest, "welcome"));
            welcomeService.saveWelcome(welcomeDTO, information);

            SubwayDTO subwayDTO = new SubwayDTO();
            subwayDTO.setSubwayMessage(parsingEditorState.parsingEditorStateToString(jsonRequest, "subway"));
            subwayService.saveSubway(subwayDTO, information);

            BusDTO busDTO = new BusDTO();
            busDTO.setBusMessage(parsingEditorState.parsingEditorStateToString(jsonRequest, "bus"));
            busService.saveBus(busDTO, information);

            CarDTO carDTO = new CarDTO();
            carDTO.setCarMessage(parsingEditorState.parsingEditorStateToString(jsonRequest, "car"));
            carService.saveCar(carDTO, information);

            EtcDTO etcDTO = new EtcDTO();
            etcDTO.setTransportType(parsingEditorState.parsingInsideJsonObject(jsonRequest, "etc", "transport_type"));
            etcDTO.setInfo(parsingEditorState.parsingInsideEditorStateToString(jsonRequest, "etc", "info"));
            etcService.saveEtc(etcDTO, information);


            String[] arrayMultipartFile = new String[files.size()];
            for (int i = 0; i < files.size(); i++) {
                String prefix;
                if (i == 0) {
                    prefix = "mainImage";
                } else if (i == 1) {
                    prefix = "thumbnail";
                } else {
                    prefix = "Images" + (i -1);
                }
//                String prefix = (i == 0) ? "mainImage" : "Images" + i;
                arrayMultipartFile[i] = s3Service.uploadFile(files.get(i), prefix);
            }
            s3Service.saveImagesUrl(arrayMultipartFile, information);
        } catch (IOException e) {
            response = new WecaResponse(500, "File upload failed: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }

        response = new WecaResponse(200, "ok");
        return ResponseEntity.ok(response);
    }
}