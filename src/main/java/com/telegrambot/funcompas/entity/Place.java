package com.telegrambot.funcompas.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
@Table(name = "places")
public class Place {

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

    //@OneToOne
    @Embedded
    @AttributeOverrides(value = {@AttributeOverride(name = "open", column = @Column(name = "open")), @AttributeOverride(name = "close", column = @Column(name = "close"))})
    private WorkingTime workingTime;


    @ManyToOne
    private PlaceCategory categories;

    public PlaceCategory getCategories() {
        return categories;
    }

    public void setCategories(PlaceCategory categories) {
        this.categories = categories;
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

    public WorkingTime getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(WorkingTime workingTime) {
        this.workingTime = workingTime;
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

    public Place( String name, WorkingTime workingTime, String description, Address address, Contact contact) {
        this.name = name;
  this.workingTime = workingTime;
        this.description = description;
        this.address = address;
        this.contact = contact;
    }
}
