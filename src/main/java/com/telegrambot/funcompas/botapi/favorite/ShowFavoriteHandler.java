package com.telegrambot.funcompas.botapi.favorite;

import com.telegrambot.funcompas.botapi.BotState;
import com.telegrambot.funcompas.botapi.handlers.InputMessageHandler;
import com.telegrambot.funcompas.cache.UserDataCache;
import com.telegrambot.funcompas.entity.UserProfileData;
import com.telegrambot.funcompas.service.UsersProfileDataService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;


@Component
public class ShowFavoriteHandler implements InputMessageHandler {

    private final UsersProfileDataService profileDataService;
    private final UserDataCache userDataCache;

    public ShowFavoriteHandler(UsersProfileDataService profileDataService, UserDataCache userDataCache) {
        this.profileDataService = profileDataService;
        this.userDataCache = userDataCache;
    }

    @Override
    public SendMessage handle(Message message) {
        SendMessage userReply;
        final int userId = message.getFrom().getId();
        final UserProfileData profileData = profileDataService.getUserProfileData(message.getChatId());

        userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_HELP_MENU);
        if (profileData != null) {
            userReply = new SendMessage(message.getChatId(), "Понравившиеся: \n" + profileDataService.getUserProfileData(message.getChatId()).getFavorite().toString()).setParseMode("HTML");

        } else {
            userReply = new SendMessage(message.getChatId(), "Нет данных!");
        }
        return userReply;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SEE_FAVORITE;
    }
}

