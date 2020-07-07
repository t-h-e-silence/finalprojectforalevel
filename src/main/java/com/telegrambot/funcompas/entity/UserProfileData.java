package com.telegrambot.funcompas.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Entity
@Table(name = "users")
public class UserProfileData implements Serializable {

    @Id
    Long chatId;

    public UserProfileData() {
    }

    @ManyToMany
    private List<Place> favorite = new ArrayList<>();

    public List<Place> getFavorite() {
        return favorite;
    }

    public void addFavorite(Optional<Place> place) {
        favorite.add(place.get());
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
