package com.example.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "laptops")
public class Laptop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String model;
    private Double price;
    private Integer cores;
    private Boolean offer;
    private Integer releaseYear;

    public Laptop() {
    }

    public Laptop(Long id, String model, Double price, Integer cores, Boolean offer, Integer releaseYear) {
        this.id = id;
        this.model = model;
        this.price = price;
        this.cores = cores;
        this.offer = offer;
        this.releaseYear = releaseYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCores() {
        return cores;
    }

    public void setCores(Integer cores) {
        this.cores = cores;
    }

    public Boolean getOffer() {
        return offer;
    }

    public void setOffer(Boolean offer) {
        this.offer = offer;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", cores=" + cores +
                ", offer=" + offer +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
