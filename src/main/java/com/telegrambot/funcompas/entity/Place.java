package com.telegrambot.funcompas.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


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

    @ManyToMany(mappedBy = "favorite")
    private Set<UserProfileData> user=new HashSet<>();

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

    public  Set<UserProfileData> getUser() {
        return  user;
    }

    public void setUser(Set<UserProfileData> user) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(getId(), place.getId()) &&
                Objects.equals(getName(), place.getName()) &&
                Objects.equals(getDescription(), place.getDescription()) &&
                Objects.equals(getAddress(), place.getAddress()) &&
                Objects.equals(getContact(), place.getContact()) &&
                Objects.equals(getWorkingTimeOpen(), place.getWorkingTimeOpen()) &&
                Objects.equals(getWorkingTimeClose(), place.getWorkingTimeClose()) &&
                Objects.equals(getCategories(), place.getCategories()) &&
                Objects.equals(getUser(), place.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getAddress(), getContact(), getWorkingTimeOpen(), getWorkingTimeClose(), getCategories(), getUser());
    }
}
