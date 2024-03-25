package com.example.weddingCard.dto;

import com.example.weddingCard.entity.Welcome;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WelcomeDTO {

    @JsonProperty("welcome")
    private String welcomeMessage;

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }
}
