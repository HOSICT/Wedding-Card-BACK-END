package com.example.weddingCard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarDTO {

    @JsonProperty("car")
    private String carMessage;

    public String getCarMessage() {
        return carMessage;
    }

    public void setCarMessage(String carMessage) {
        this.carMessage = carMessage;
    }
}
