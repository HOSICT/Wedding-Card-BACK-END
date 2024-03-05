package com.example.weddingCard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoadDTO {

    private String subway;
    private String bus;
    private String car;
    @JsonProperty("rent_bus")
    private String rentBus;
    private String etc;

    public String getSubway() {
        return subway;
    }

    public void setSubway(String subway) {
        this.subway = subway;
    }

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getRentBus() {
        return rentBus;
    }

    public void setRentBus(String rentBus) {
        this.rentBus = rentBus;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }
}
