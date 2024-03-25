package com.example.weddingCard.controller;

import com.example.weddingCard.entity.Accounts;
import com.example.weddingCard.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InquiryController {

    @Autowired
    AccountsService accountsService;

}
