package com.example.weddingCard.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {

    @PostMapping("/test")
    public Map<String, Object> receiveJsonData(@RequestBody Map<String, Object> jsonData) {

        System.out.println(jsonData);

        return jsonData;
    }
}
