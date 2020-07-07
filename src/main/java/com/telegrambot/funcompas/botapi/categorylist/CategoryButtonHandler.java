package com.telegrambot.funcompas.botapi.categorylist;

import com.telegrambot.funcompas.botapi.BotState;
import com.telegrambot.funcompas.botapi.handlers.ButtonHandler;
import com.telegrambot.funcompas.cache.UserDataCache;
import com.telegrambot.funcompas.entity.Place;
import com.telegrambot.funcompas.service.PlaceMethodsService;
import com.telegrambot.funcompas.service.MainMenuService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import java.util.List;

@Component
public class CategoryButtonHandler implements ButtonHandler {
    private final UserDataCache userDataCache;
    private final MainMenuService mainMenuService;
    private final PlaceMethodsService placeMethodsService;

    public CategoryButtonHandler(UserDataCache userDataCache, MainMenuService mainMenuService,
                                 PlaceMethodsService placeMethodsService) {
        this.userDataCache = userDataCache;
        this.mainMenuService = mainMenuService;
        this.placeMethodsService = placeMethodsService;
    }

    @Override
    public BotApiMethod<?> handle(CallbackQuery buttonQuery){
        final long chatId = buttonQuery.getMessage().getChatId();
        final int userId = buttonQuery.getFrom().getId();
        List<Place> placeList;
        BotApiMethod<?> callBackAnswer = mainMenuService.getMainMenuMessage(chatId, "Воспользуйтесь главным меню");

        switch (buttonQuery.getData()) {
            case "buttonCinema":
                placeList = placeMethodsService.getByCategory("Кинотеатры");
                callBackAnswer = new SendMessage(chatId, "Найти по категории:\nКинотеатры \n" + placeList.toString()).setParseMode("HTML");
                break;
            case "buttonCafe":
                placeList = placeMethodsService.getByCategory("Кафе");
                callBackAnswer = new SendMessage(chatId, "Найти по категории:\n Кафе \n" + placeList.toString()).setParseMode("HTML");
                break;
            case "buttonClub":
                placeList = placeMethodsService.getByCategory("Клубы");
                callBackAnswer = new SendMessage(chatId, "Найти по категории:\n Клубы \n" + placeList.toString()).setParseMode("HTML");
                break;
            case "buttonResto":
                placeList = placeMethodsService.getByCategory("Рестораны");
                callBackAnswer = new SendMessage(chatId, "Найти по категории:\n Рестораны \n" + placeList.toString()).setParseMode("HTML");
                break;
            case "buttonShop":
                placeList = placeMethodsService.getByCategory("Tорговые центры");
                callBackAnswer = new SendMessage(chatId, "Найти по категории:\n Tорговые центры \n" + placeList.toString()).setParseMode("HTML");
                break;
            case "buttonQuest":
                placeList = placeMethodsService.getByCategory("Квест-комнаты");
                callBackAnswer = new SendMessage(chatId, "Найти по категории:\n Квест-комнаты \n" + placeList.toString()).setParseMode("HTML");
                break;
            default:
                userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_HELP_MENU);
                break;
        }

      userDataCache.setUsersCurrentBotState(userId, BotState.SHOW_HELP_MENU);
        return callBackAnswer;
    }

    @Override
    public BotState getHandlerName() {
        return BotState.SEE_BY_CATEGORY;
    }

}
