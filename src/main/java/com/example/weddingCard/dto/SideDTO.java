package com.example.weddingCard.dto;

public class SideDTO {

    private AccountsDTO me;
    private AccountsDTO father;
    private AccountsDTO mother;
    private String relationship;

    public AccountsDTO getMe() {
        return me;
    }

    public void setMe(AccountsDTO me) {
        this.me = me;
    }

    public AccountsDTO getFather() {
        return father;
    }

    public void setFather(AccountsDTO father) {
        this.father = father;
    }

    public AccountsDTO getMother() {
        return mother;
    }

    public void setMother(AccountsDTO mother) {
        this.mother = mother;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
