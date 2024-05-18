package com.logistics.ejb.entity;

import jakarta.persistence.*;

@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private double weight;
    @Version
    private long version;

    @ManyToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    public Cargo() {
    }

    public Cargo(String description, double weight, long version) {
        this.description = description;
        this.weight = weight;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }
}