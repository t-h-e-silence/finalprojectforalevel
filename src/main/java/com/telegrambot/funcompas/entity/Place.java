package com.telegrambot.funcompas.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Component
@Entity
@Table(name = "places")
public class Place implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    @SequenceGenerator(name="my_seq", sequenceName = "my_seq", allocationSize = 1)
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

    @ManyToMany
    private List<UserProfileData> user;

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

    public  List<UserProfileData> getUser() {
        return  user;
    }

    public void setUser( List<UserProfileData> user) {
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

    public Place(String name, WorkingTimeOpen workingTimeOpen, WorkingTimeClose workingTimeClose, String description, Address address, Contact contact) {
        this.name = name;
        this.workingTimeOpen = workingTimeOpen;
        this.workingTimeClose = workingTimeClose;
        this.description = description;
        this.address = address;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return String.format("%n%nНазвание: <b> %s%n </b> id: <b> %s </b>%nОписание: %s%n%n  %s%n%n Контакты:%n %s%n" +
                        "Время работы: %s%n %s", getName(), getId(), getDescription(), getAddress(), getContact(),
                getWorkingTimeOpen().toString(), getWorkingTimeClose().toString());
    }

}
