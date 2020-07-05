package com.telegrambot.funcompas.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
public class UserProfileData implements Serializable {
    @javax.persistence.Id
    @Id
    @GeneratedValue
    Integer id;

    @Column
    Long chatId;

    public UserProfileData() {
    }

    @OneToMany
    private List<Place> favorite=new ArrayList<>();

    public List<Place> getFavorite() {
        return favorite;
    }

    public void setFavorite(List<Place> favorite) {
        this.favorite = favorite;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }


    @Override
    public String toString() {
        return "UserProfileData{" +
                ", chatId=" + chatId +
                '}';

    }
}
