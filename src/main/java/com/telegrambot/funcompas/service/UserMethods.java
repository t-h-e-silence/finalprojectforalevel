package com.telegrambot.funcompas.service;

import com.telegrambot.funcompas.entity.UserProfileData;

public interface UserMethods {

    UserProfileData findByChatId(Long chatId);

    void save(UserProfileData userProfile);

    UserProfileData getUserProfileData(Long chatId);
}
