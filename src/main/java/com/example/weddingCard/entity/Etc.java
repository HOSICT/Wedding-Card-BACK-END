package com.example.weddingCard.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "etc")
public class Etc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "etc_id")
    private Integer etcId;
    @ManyToOne
    @JoinColumn(name = "wedding_id")
    private Information weddingId;
    @Column(name = "transport_type")
    private String transportType;
    @Column(columnDefinition = "TEXT")
    private String info;

    public Integer getEtcId() {
        return etcId;
    }

    public void setEtcId(Integer etcId) {
        this.etcId = etcId;
    }

    public Information getWeddingId() {
        return weddingId;
    }

    public void setWeddingId(Information weddingId) {
        this.weddingId = weddingId;
    }

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
