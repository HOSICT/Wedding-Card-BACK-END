package com.example.weddingCard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BusDTO {

    @JsonProperty("bus")
    private String busMessage;

    public String getBusMessage() {
        return busMessage;
    }

    public void setBusMessage(String busMessage) {
        this.busMessage = busMessage;
    }
}
