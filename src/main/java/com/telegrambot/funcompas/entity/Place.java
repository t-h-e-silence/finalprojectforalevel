package com.telegrambot.funcompas.entity;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;


@Component
@Entity
@Table(name = "places")
public class Place implements Serializable {

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    public Place() {
    }

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @OneToOne
    private Address address;

    @OneToOne
    private Contact contact;

    @Embedded
    private WorkingTimeOpen workingTimeOpen;

    @Embedded
    private WorkingTimeClose workingTimeClose;

    @ManyToOne
    private PlaceCategory categories;

    @ManyToOne
    private UserProfileData user;

    public PlaceCategory getCategories() {
        return categories;
    }

    public void setCategories(PlaceCategory categories) {
        this.categories = categories;
    }

    public Integer getId() {
        return id;
    }

    public WorkingTimeClose getWorkingTimeClose() {
        return workingTimeClose;
    }

    public void setWorkingTimeClose(WorkingTimeClose workingTimeClose) {
        this.workingTimeClose = workingTimeClose;
    }

    public UserProfileData getUser() {
        return user;
    }

    public void setUser(UserProfileData user) {
        this.user = user;
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

    public WorkingTimeOpen getWorkingTimeOpen() {
        return workingTimeOpen;
    }

    public void setWorkingTimeOpen(WorkingTimeOpen workingTimeOpen) {
        this.workingTimeOpen = workingTimeOpen;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Place(String name, WorkingTimeOpen workingTimeOpen, WorkingTimeClose workingTimeClose, String description, Address address, Contact contact, UserProfileData user) {
        this.name = name;
        this.workingTimeOpen = workingTimeOpen;
        this.workingTimeClose = workingTimeClose;
        this.description = description;
        this.address = address;
        this.contact = contact;
        this.user = user;
    }

    @Override
    public String toString() {
        return String.format("\nНазвание: %s%n\nОписание: %s%n\n  %s%n\n Контакты: %s%n" +
                        "Время работы: %s%n", getName(), getDescription(), getAddress(), getContact(),
                getWorkingTimeOpen().toString());
    }

}
