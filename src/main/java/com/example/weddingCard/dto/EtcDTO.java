package com.example.weddingCard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EtcDTO {

    @JsonProperty("transport_type")
    private String transportType;

    private String info;

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
