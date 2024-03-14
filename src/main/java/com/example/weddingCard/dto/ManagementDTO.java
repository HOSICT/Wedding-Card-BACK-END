package com.example.weddingCard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ManagementDTO {

    @JsonProperty("management_password")
    private String managementPassword;

    public String getManagementPassword() {
        return managementPassword;
    }

    public void setManagementPassword(String managementPassword) {
        this.managementPassword = managementPassword;
    }

}
