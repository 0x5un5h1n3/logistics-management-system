package com.logistics.ejb.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origin;
    private String destination;
    private Date shippingDate;

    @Column(nullable = true)
    private String optimizedRoute;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cargo> cargos;

    public Shipment() {
    }

    public Shipment(String origin, String destination, Date shippingDate, String optimizedRoute) {
        this.origin = origin;
        this.destination = destination;
        this.shippingDate = shippingDate;
        this.optimizedRoute = optimizedRoute;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }

    public String getOptimizedRoute() {
        return optimizedRoute;
    }

    public void setOptimizedRoute(String optimizedRoute) {
        this.optimizedRoute = optimizedRoute;
    }
}
