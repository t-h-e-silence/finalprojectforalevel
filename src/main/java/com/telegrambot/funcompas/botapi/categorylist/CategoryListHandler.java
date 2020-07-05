package com.telegrambot.funcompas.botapi.categorylist;

import com.telegrambot.funcompas.botapi.BotState;
import com.telegrambot.funcompas.botapi.handlers.InputMessageHandler;
import com.telegrambot.funcompas.cache.UserDataCache;
import com.telegrambot.funcompas.entity.UserProfileData;
import com.telegrambot.funcompas.service.ReplyMessagesService;
import com.telegrambot.funcompas.service.UsersProfileDataService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryListHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessagesService messagesService;
    private UsersProfileDataService profileDataService;

    public CategoryListHandler(UserDataCache userDataCache, ReplyMessagesService messagesService,
                               UsersProfileDataService profileDataService) {
        this.userDataCache = userDataCache;
        this.messagesService = messagesService;
        this.profileDataService = profileDataService;
    }

    @Override
    public SendMessage handle(Message message) {
        if (userDataCache.getUsersCurrentBotState(message.getFrom().getId()).equals(BotState.START_SEARCH)) {
            userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotState.CATEGORIES);
        }
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.START_SEARCH;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        UserProfileData profileData = userDataCache.getUserProfileData(userId);
        BotState botState = userDataCache.getUsersCurrentBotState(userId);

        SendMessage replyToUser = null;

        if (botState.equals(BotState.CATEGORIES)) {
            replyToUser = messagesService.getReplyMessage(chatId, "reply.categories");
            replyToUser.setReplyMarkup(getCategoryButtonsMarkup());
    }

        userDataCache.saveUserProfileData(userId, profileData);
        return replyToUser;
    }

    private InlineKeyboardMarkup getCategoryButtonsMarkup() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton buttonCinema = new InlineKeyboardButton().setText("Кинотеатры");
        InlineKeyboardButton buttonCafe = new InlineKeyboardButton().setText("Кафе");
        InlineKeyboardButton buttonClub = new InlineKeyboardButton().setText("Клубы");
        InlineKeyboardButton buttonShop = new InlineKeyboardButton().setText("Tорговые центры");
        InlineKeyboardButton buttonResto = new InlineKeyboardButton().setText("Рестораны");
        InlineKeyboardButton buttonQuest = new InlineKeyboardButton().setText("Квест-комнаты");

        buttonCinema.setCallbackData("buttonCinema");
        buttonClub.setCallbackData("buttonClub");
        buttonCafe.setCallbackData("buttonCafe");
        buttonShop.setCallbackData("buttonShop");
        buttonResto.setCallbackData("buttonResto");
        buttonQuest.setCallbackData("buttonQuest");


        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(buttonCinema);
        keyboardButtonsRow1.add(buttonClub);
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(buttonCafe);
        keyboardButtonsRow2.add(buttonShop);
        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
        keyboardButtonsRow3.add(buttonResto);
        keyboardButtonsRow3.add(buttonQuest);

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        rowList.add(keyboardButtonsRow3);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}



