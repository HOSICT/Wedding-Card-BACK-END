package com.example.weddingCard.controller;

import com.example.weddingCard.entity.Information;
import com.example.weddingCard.repository.InformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inquiry")
public class InquiryController {

    @Autowired
    private InformationRepository informationRepository;

    @GetMapping
    public List<Information> getInformation() {
        return informationRepository.findAll();
    }
}
