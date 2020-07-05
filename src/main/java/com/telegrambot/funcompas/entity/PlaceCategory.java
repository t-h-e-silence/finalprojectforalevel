package com.telegrambot.funcompas.entity;

import org.hibernate.annotations.NaturalId;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Component
@Entity
@Table(name = "group_categories")
public class PlaceCategory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @NaturalId
    @Column(nullable = false)
    private String name;


    @OneToMany
    private final Set<Place> categories = new HashSet<>();

    public Set<Place> getCategories() {
        return categories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PlaceCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categories=" + categories +
                '}';
    }
}

