package com.example.weddingCard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ManagementDTO {

    @JsonProperty("management_password")
    private String managementPassword;

    private Integer bgm;

    public String getManagementPassword() {
        return managementPassword;
    }

    public void setManagementPassword(String managementPassword) {
        this.managementPassword = managementPassword;
    }

    public Integer getBgm() {
        return bgm;
    }

    public void setBgm(Integer bgm) {
        this.bgm = bgm;
    }
}
