package com.telegrambot.funcompas.botapi.addfavorite;

import com.telegrambot.funcompas.botapi.BotState;
import com.telegrambot.funcompas.botapi.handlers.InputMessageHandler;
import com.telegrambot.funcompas.cache.UserDataCache;
import com.telegrambot.funcompas.entity.Place;
import com.telegrambot.funcompas.entity.UserProfileData;
import com.telegrambot.funcompas.service.PlaceMethodsService;
import com.telegrambot.funcompas.service.ReplyMessagesService;
import com.telegrambot.funcompas.service.UsersProfileDataService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddFavoriteHandler implements InputMessageHandler {

        private final UserDataCache userDataCache;
        private final ReplyMessagesService messagesService;
        private final PlaceMethodsService placeMethodsService;
    private  final UsersProfileDataService profileDataService;
    public AddFavoriteHandler(UserDataCache userDataCache, ReplyMessagesService messagesService
            , PlaceMethodsService placeMethodsService, UsersProfileDataService profileDataService) {
            this.userDataCache = userDataCache;
            this.messagesService = messagesService;
        this.placeMethodsService = placeMethodsService;
        this.profileDataService = profileDataService;
    }

        @Override
        public SendMessage handle(Message message) {
            if (userDataCache.getUsersCurrentBotState(message.getFrom().getId()).equals(BotState.ADD_TO_FAVORITE)) {
                userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotState.ASK_ID);
            }
            return processUsersInput(message);
        }

        @Override
        public BotState getHandlerName() {
            return BotState.ADD_TO_FAVORITE;
        }

        private SendMessage processUsersInput(Message inputMsg) {
            String usersAnswer = inputMsg.getText();
            int userId = inputMsg.getFrom().getId();
            long chatId = inputMsg.getChatId();

            UserProfileData profileData = userDataCache.getUserProfileData(userId);
            BotState botState = userDataCache.getUsersCurrentBotState(userId);

            SendMessage replyToUser = null;

            if (botState.equals(BotState.ASK_ID)) {
                replyToUser = messagesService.getReplyMessage(chatId, "reply.addToFavorite");
                userDataCache.setUsersCurrentBotState(userId, BotState.PROFILE_FILLED);
            }

            if (botState.equals(BotState.PROFILE_FILLED)) {
                profileData.setChatId(chatId);
                Optional<Place> favorite=placeMethodsService.findPlaceById(Integer.parseInt(usersAnswer));
                profileData.addFavorite(favorite);
                profileDataService.save(profileData);

                userDataCache.setUsersCurrentBotState(userId, BotState.MAIN_MENU);

                String profileFilledMessage = messagesService.getReplyText("reply.profileFilled",
                        profileData.getFavorite().toString());
                replyToUser=new SendMessage(chatId, profileFilledMessage);

            }

            userDataCache.saveUserProfileData(userId, profileData);

            return replyToUser;
        }

    }


    /*    private UserDataCache userDataCache;
        private UsersProfileDataService profileDataService;

        public AddFavoriteHandler(UserDataCache userDataCache, UsersProfileDataService profileDataService) {
            this.userDataCache = userDataCache;
            this.profileDataService = profileDataService;
        }

        @Override
        public SendMessage handle(Message message) {
            SendMessage userReply;
            final int userId = message.getFrom().getId();
            final UserProfileData profileData = profileDataService.getUserProfileData(message.getChatId());

            userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_HELP_MENU);
            if (profileData != null) {
                userReply = new SendMessage(message.getChatId(),
                        String.format("%s%n-------------------%n%s", "Данные по вашей анкете:", profileData.toString()));
            } else {
                userReply = new SendMessage(message.getChatId(), "Такой анкеты в БД не существует !");
            }

            return userReply;
        }

        @Override
        public BotState getHandlerName() {
            return BotState.SHOW_USER_PROFILE;
        }
    }

}







/*
    private final UserDataCache userDataCache;
    private final ReplyMessagesService messagesService;

    public AddFavoriteHandler(UserDataCache userDataCache, ReplyMessagesService messagesService) {
        this.userDataCache = userDataCache;
        this.messagesService = messagesService;
    }

    @Override
    public SendMessage handle(Message message) {
        if (userDataCache.getUsersCurrentBotState(message.getFrom().getId()).equals(BotState.SEE_BY_CATEGORY)) {
            userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotState.ADD_TO_FAVORITE);
        }
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.MAIN_MENU;//ASK_ID;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        UserProfileData profileData = userDataCache.getUserProfileData(userId);
        BotState botState = userDataCache.getUsersCurrentBotState(userId);

        SendMessage replyToUser = null;

        if (botState.equals(BotState.ADD_TO_FAVORITE)) {
            replyToUser = messagesService.getReplyMessage(chatId, "reply.addToFavorite");
         //   replyToUser.setReplyMarkup(getCategoryButtonsMarkup());
        }
        userDataCache.saveUserProfileData(userId, profileData);
        return replyToUser;
    }

  /*  private InlineKeyboardMarkup getCategoryButtonsMarkup() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonIsItFavorite = new InlineKeyboardButton().setText("Добавить в Избранное");

        buttonIsItFavorite.setCallbackData("buttonIsItFavorite");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonIsItFavorite);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);

        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}

*/
