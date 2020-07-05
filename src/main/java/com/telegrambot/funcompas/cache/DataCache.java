package com.telegrambot.funcompas.cache;


import com.telegrambot.funcompas.botapi.BotState;
import com.telegrambot.funcompas.entity.UserProfileData;

public interface DataCache {
    void setUsersCurrentBotState(int userId, BotState botState);

    BotState getUsersCurrentBotState(int userId);

    UserProfileData getUserProfileData(int userId);

    void saveUserProfileData(int userId, UserProfileData userProfileData);
}
