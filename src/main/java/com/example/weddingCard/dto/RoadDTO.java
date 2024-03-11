package com.example.weddingCard.dto;

public class RoadDTO {

    private String subway;
    private String bus;
    private String car;
    private EtcDTO etc;

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

    public EtcDTO getEtc() {
        return etc;
    }

    public void setEtc(EtcDTO etc) {
        this.etc = etc;
    }
}
