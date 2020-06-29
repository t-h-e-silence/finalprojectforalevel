package com.telegrambot.funcompas.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "group_categories")
public class PlaceCategory {
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

}

