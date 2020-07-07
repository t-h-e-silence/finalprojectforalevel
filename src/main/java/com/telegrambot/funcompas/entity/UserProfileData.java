package com.telegrambot.funcompas.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "users")
public class UserProfileData implements Serializable {

    @Id
    Long chatId;

    public UserProfileData() {
    }

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinTable(
            name = "users_favorite",
            joinColumns = { @JoinColumn(name = "user_profile_data_chat_id") },
            inverseJoinColumns = { @JoinColumn(name = "favorite_id") }
    )
    private Set<Place> favorite = new HashSet<>();

    public Set<Place> getFavorite() {
        return favorite;
    }

    public void addFavorite(Optional<Place> place) {
        favorite.add(place.get());
    }

    public void setFavorite(Set<Place> favorite) {
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
