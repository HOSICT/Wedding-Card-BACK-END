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
    @JoinColumn(name = "road_id")
    private Road roadId;

    @Column(name = "transport_type")
    private String transportType;

    private String info;

    public Integer getEtcId() {
        return etcId;
    }

    public void setEtcId(Integer etcId) {
        this.etcId = etcId;
    }

    public Road getRoadId() {
        return roadId;
    }

    public void setRoadId(Road roadId) {
        this.roadId = roadId;
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
