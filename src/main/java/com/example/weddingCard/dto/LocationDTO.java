package com.example.weddingCard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationDTO {

    @JsonProperty("wedding_hall")
    private String weddingHall;
    private String address;
    private double latitude;
    private double longitude;

    public String getWeddingHall() {
        return weddingHall;
    }

    public void setWeddingHall(String weddingHall) {
        this.weddingHall = weddingHall;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
