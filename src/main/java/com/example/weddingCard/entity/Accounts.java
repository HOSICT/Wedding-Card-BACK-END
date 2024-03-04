package com.example.weddingCard.entity;

import com.example.weddingCard.enums.Relation;
import com.example.weddingCard.enums.Side;
import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;
    @ManyToOne
    @JoinColumn(name = "wedding_id")
    private Information weddingId;
    @Enumerated(EnumType.STRING)
    private Relation relation;
    @Enumerated(EnumType.STRING)
    private Side side;
    private String name;
    private String english;
    private String bank;
    private String account;

    public Integer getAccountId() {
        return accountId;
    }

    public Information getWeddingId() {
        return weddingId;
    }

    public Relation getRelation() {
        return relation;
    }

    public Side getSide() {
        return side;
    }

    public String getName() {
        return name;
    }

    public String getEnglish() {
        return english;
    }

    public String getBank() {
        return bank;
    }

    public String getAccount() {
        return account;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public void setWeddingId(Information weddingId) {
        this.weddingId = weddingId;
    }

    public void setRelation(Relation relation) {
        this.relation = relation;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public void setAccount(String account) {
        this.account = account;
    }

}
