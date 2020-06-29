package com.telegrambot.funcompas.entity;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    public Address() {
    }

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private Integer number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Address( String city, String street, Integer number) {
        this.city = city;
        this.street = street;
        this.number = number;
    }
}
