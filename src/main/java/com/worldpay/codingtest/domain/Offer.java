package com.worldpay.codingtest.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Offer {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private String currency;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date listedOn;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private Date expiresOn;

    public Offer(){ }

    public Offer(Long id, String title, String description, Double price, String currency, Date listedOn, Date expiresOn) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.listedOn = listedOn;
        this.expiresOn = expiresOn;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public Date getListedOn() {
        return listedOn;
    }

    public Date getExpiresOn() {
        return expiresOn;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setListedOn(Date listedOn) {
        this.listedOn = listedOn;
    }

    public void setExpiresOn(Date expiresOn) {
        this.expiresOn = expiresOn;
    }
}
