package com.telegrambot.funcompas.botapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.telegrambot.funcompas.appconfig.TelegramBot;
import com.telegrambot.funcompas.botapi.categorylist.CategoryButtonHandler;
import com.telegrambot.funcompas.cache.UserDataCache;
import com.telegrambot.funcompas.service.PlaceMethodsService;
import com.telegrambot.funcompas.service.MainMenuService;
import com.telegrambot.funcompas.service.ReplyMessagesService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TelegramFacade {
    private BotStateContext botStateContext;
    private UserDataCache userDataCache;
    private MainMenuService mainMenuService;
    private TelegramBot telegramBot;
    private ReplyMessagesService messagesService;
    private PlaceMethodsService placeMethodsService;
    private CategoryButtonHandler buttonHandler;
    private static final Logger log = LoggerFactory.getLogger(TelegramFacade.class);

    public TelegramFacade(BotStateContext botStateContext, UserDataCache userDataCache, MainMenuService mainMenuService,
                          @Lazy TelegramBot telegramBot, ReplyMessagesService messagesService, PlaceMethodsService placeMethodsService, CategoryButtonHandler buttonHandler) {
        this.botStateContext = botStateContext;
        this.userDataCache = userDataCache;
        this.mainMenuService = mainMenuService;
        this.telegramBot = telegramBot;
        this.messagesService = messagesService;
        this.placeMethodsService = placeMethodsService;
        this.buttonHandler = buttonHandler;
    }

    public BotApiMethod<?> handleUpdate(Update update) {
        SendMessage replyMessage = null;

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            log.info("New callbackQuery from User: {}, userId: {}, with data: {}", update.getCallbackQuery().getFrom().getUserName(),
                    callbackQuery.getFrom().getId(), update.getCallbackQuery().getData());
            buttonHandler.handle(callbackQuery);
            return buttonHandler.handle(callbackQuery); }

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
         log.info("New message from User:{}, userId: {}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getFrom().getId(), message.getChatId(), message.getText());
            replyMessage = handleInputMessage(message);
        }

        return replyMessage;
    }

    private SendMessage handleInputMessage(Message message) {
        String inputMsg = message.getText();
        int userId = message.getFrom().getId();
        long chatId = message.getChatId();
        BotState botState;
        SendMessage replyMessage;

        switch (inputMsg) {
            case "/start":
            case "Помощь":
                botState = BotState.SHOW_HELP_MENU;
                break;
            case "Начать поиск":
                botState = BotState.START_SEARCH;
                break;
            case "Понравившиеся":
                botState = BotState.SEE_FAVORITE;
                break;
            case "Кинотеатры":
                botState = BotState.SEE_BY_CATEGORY;
                break;
            default:
                botState = userDataCache.getUsersCurrentBotState(userId);
                break;
        }

        userDataCache.setUsersCurrentBotState(userId, botState);

        replyMessage = botStateContext.processInputMessage(botState, message);

        return replyMessage;
    }

    private AnswerCallbackQuery sendAnswerCallbackQuery(String text, boolean alert, CallbackQuery callbackquery) {
        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(callbackquery.getId());
        answerCallbackQuery.setShowAlert(alert);
        answerCallbackQuery.setText(text);
        return answerCallbackQuery;
    }

}
