package com.example.weddingCard.response;

public class WecaResponse {

    private int status;
    private String mag;

    public WecaResponse(int status, String mag) {
        this.status = status;
        this.mag = mag;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }
}
