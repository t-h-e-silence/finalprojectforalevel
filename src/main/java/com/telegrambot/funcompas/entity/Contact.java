package com.telegrambot.funcompas.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Component
@Entity
@Table(name = "contact")
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;

    public Contact() {
    }

    @Column
    private BigInteger number;

    @Column
    private String name;

    @Column
    private String site;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getNumber() {
        return number;
    }

    public void setNumber(BigInteger number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Contact(BigInteger number, String name, String site) {
        this.number = number;
        this.name = name;
        this.site = site;
    }

   @Override
    public String toString() {
        return String.format("%s%n  %s, %s%n", getName(), getNumber(), getSite());

    }

}
