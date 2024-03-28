package com.example.weddingCard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubwayDTO {

    @JsonProperty("subway")
    private String subwayMessage;

    public String getSubwayMessage() {
        return subwayMessage;
    }

    public void setSubwayMessage(String subwayMessage) {
        this.subwayMessage = subwayMessage;
    }
}
