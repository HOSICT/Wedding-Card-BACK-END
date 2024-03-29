package com.example.weddingCard.controller;

import com.example.weddingCard.entity.*;
import com.example.weddingCard.service.*;
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
import java.util.stream.Collectors;

@RestController
public class InquiryController {

    private final InformationService informationService;
    private final LocationService locationService;
    private final WelcomeService welcomeService;
    private final ContentsService contentsService;
    private final ManagementService managementService;
    private final AccountsService accountsService;
    private final SubwayService subwayService;
    private final BusService busService;
    private final CarService carService;
    private final EtcService etcService;
    private final OpenGraphService openGraphService;

    public InquiryController(InformationService informationService, LocationService locationService, WelcomeService welcomeService, ContentsService contentsService, ManagementService managementService, AccountsService accountsService, SubwayService subwayService, BusService busService, CarService carService, EtcService etcService, OpenGraphService openGraphService) {
        this.informationService = informationService;
        this.locationService = locationService;
        this.welcomeService = welcomeService;
        this.contentsService = contentsService;
        this.managementService = managementService;
        this.accountsService = accountsService;
        this.subwayService = subwayService;
        this.busService = busService;
        this.carService = carService;
        this.etcService = etcService;
        this.openGraphService = openGraphService;
    }

    @GetMapping("/inquiry")
    public ResponseEntity<Map<String, Object>> getInformation(@RequestHeader(name = "uid") WecaUser uid) {
        List<Information> informationList = informationService.findInformationByUserId(uid);

        List<Location> locationList = locationService.findLocationByWeddingId(informationList);
        List<Welcome> welcomeList = welcomeService.findWelcomeByWeddingId(informationList);
        List<Contents> contentsList = contentsService.findContentsByWeddingId(informationList);
        List<Management> managementList = managementService.findManagementByWeddingId(informationList);
        List<Accounts> accountsList = accountsService.findAccountsByWeddingId(informationList);
        List<Subway> subwayList = subwayService.findSubwayByWeddingId(informationList);
        List<Bus> busList = busService.findBusByWeddingId(informationList);
        List<Car> carList = carService.findCarByWeddingId(informationList);
        List<Etc> etcList = etcService.findEtcByWeddingId(informationList);
        List<OpenGraph> openGraphList = openGraphService.findOpenGraphByWeddingId(informationList);

        List<Map<String, Object>> informationJson = processInformation(informationList);
        Map<String, Object> locationJson = processLocation(locationList);
        Map<String, Object> contentsJson = processContents(contentsList);
        Map<String, Object> managementJson = processManagement(managementList);
        Object welcomeJson = processWelcomeMessage(welcomeList);
        Map<String, Object> accountsJson = processAccounts(accountsList);
        Object subwayJson = processSubwayMessage(subwayList);
        Object busJson = processBusMessage(busList);
        Object carJson = processCarMessage(carList);
        Object etcJson = processInfoMessage(etcList);
        Map<String, Object> openGraphJson = processOpenGraph(openGraphList);

        Map<String, Object> responseBody = informationJson.isEmpty() ? new HashMap<>() : informationJson.get(0);
        responseBody.put("location", locationJson);
        responseBody.put("welcome", welcomeJson);
        responseBody.put("contents", contentsJson);
        responseBody.put("management", managementJson);
        responseBody.put("accounts", accountsJson);
        responseBody.put("subway", subwayJson);
        responseBody.put("bus", busJson);
        responseBody.put("car", carJson);
        responseBody.put("etc", etcJson);
        responseBody.put("open_graph", openGraphJson);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    private List<Map<String, Object>> processInformation(List<Information> informationList) {
        return informationList.stream().map(information -> {
            Map<String, Object> informationMap = new HashMap<>();
            informationMap.put("date", information.getDate());
            informationMap.put("welcome_align", information.getWelcomeAlign());
            return informationMap;
        }).collect(Collectors.toList());
    }

    private Map<String, Object> processLocation(List<Location> locationList) {
        if (locationList.isEmpty()) {
            return new HashMap<>();
        }
        Location location = locationList.get(0);
        Map<String, Object> locationJson = new HashMap<>();
        locationJson.put("address", location.getAddress());
        locationJson.put("wedding_hall", location.getWeddingHall());
        locationJson.put("latitude", location.getLatitude());
        locationJson.put("longitude", location.getLongitude());

        return locationJson;
    }

    private Map<String, Object> processContents(List<Contents> contentsList) {
        if (contentsList.isEmpty()) {
            return new HashMap<>();
        }
        Contents contents = contentsList.get(0);
        Map<String, Object> contentsJson = new HashMap<>();
        contentsJson.put("video_id", contents.getVideoId());
        contentsJson.put("bgm", contents.getBgm());
        contentsJson.put("effect", contents.getEffect());
        contentsJson.put("live_url", contents.getLiveUrl());

        return contentsJson;
    }

    private Map<String, Object> processManagement(List<Management> managementList) {
        if (managementList.isEmpty()) {
            return new HashMap<>();
        }
        Management management = managementList.get(0);
        Map<String, Object> managementJson = new HashMap<>();
        managementJson.put("management_password", management.getManagementPassword());

        return managementJson;
    }

    private Map<String, Object> processOpenGraph(List<OpenGraph> openGraphList) {
        if (openGraphList.isEmpty()) {
            return new HashMap<>();
        }
        OpenGraph openGraph = openGraphList.get(0);
        Map<String, Object> openGraphJson = new HashMap<>();
        openGraphJson.put("title", openGraph.getTitle());
        openGraphJson.put("subtitle", openGraph.getSubtitle());

        return openGraphJson;
    }

    private Object processWelcomeMessage(List<Welcome> welcomeList) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (welcomeList.isEmpty()) {
            return new ArrayList<>();
        }
        String welcomeMessage = welcomeList.get(0).getWelcomeMessage();
        try {
            return objectMapper.readValue(welcomeMessage, Object.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private Object processSubwayMessage(List<Subway> subwayList) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (subwayList.isEmpty()) {
            return new ArrayList<>();
        }
        String subwayMessage = subwayList.get(0).getSubwayMessage();
        try {
            return objectMapper.readValue(subwayMessage, Object.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private Object processBusMessage(List<Bus> busList) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (busList.isEmpty()) {
            return new ArrayList<>();
        }
        String busMessage = busList.get(0).getBusMessage();
        try {
            return objectMapper.readValue(busMessage, Object.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private Object processCarMessage(List<Car> carList) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (carList.isEmpty()) {
            return new ArrayList<>();
        }
        String carMessage = carList.get(0).getCarMessage();
        try {
            return objectMapper.readValue(carMessage, Object.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private Object processInfoMessage(List<Etc> etcList) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> etcJson = new HashMap<>();
        if (!etcList.isEmpty()) {
            Etc etc = etcList.get(0);
            etcJson.put("transport_type", etc.getTransportType());

            String etcMessage = etcList.get(0).getInfo();
            try {
                Object infoJson = objectMapper.readValue(etcMessage, Object.class);
                etcJson.put("info", infoJson);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
        return etcJson;
    }

    private Map<String, Object> processAccounts(List<Accounts> accountsList) {
        Map<String, Object> accountsJson = new HashMap<>();
        Map<String, Object> husband = new HashMap<>();
        Map<String, Object> wife = new HashMap<>();

        String husbandRelationship = null;
        String wifeRelationship = null;

        for (Accounts account : accountsList) {
            Map<String, Object> accountDetails = new HashMap<>();
            accountDetails.put("name", account.getName());
            accountDetails.put("bank", account.getBank());
            accountDetails.put("account", account.getAccount());
            accountDetails.put("contact", account.getContact());

            String relation = account.getRelation().name();
            String side = account.getSide().name();

            if ("ME".equals(relation)) {
                if ("HUSBAND".equals(side)) {
                    husbandRelationship = account.getRelationship();
                } else if ("WIFE".equals(side)) {
                    wifeRelationship = account.getRelationship();
                }
            }

            if ("HUSBAND".equals(side)) {
                husband.put(relation, accountDetails);
            } else if ("WIFE".equals(side)) {
                wife.put(relation, accountDetails);
            }
        }

        if (husbandRelationship != null) {
            husband.put("relationship", husbandRelationship);
        }
        if (wifeRelationship != null) {
            wife.put("relationship", wifeRelationship);
        }

        accountsJson.put("HUSBAND", husband);
        accountsJson.put("WIFE", wife);

        return accountsJson;
    }
}
