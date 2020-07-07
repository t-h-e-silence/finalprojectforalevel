package com.telegrambot.funcompas.botapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.telegrambot.funcompas.botapi.categorylist.CategoryButtonHandler;
import com.telegrambot.funcompas.cache.UserDataCache;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;


@Component
public class TelegramFacade {
    private final BotStateContext botStateContext;
    private final UserDataCache userDataCache;
    private final CategoryButtonHandler buttonHandler;
    private static final Logger log = LoggerFactory.getLogger(TelegramFacade.class);

    public TelegramFacade(BotStateContext botStateContext, UserDataCache userDataCache,
                          CategoryButtonHandler buttonHandler) {
        this.botStateContext = botStateContext;
        this.userDataCache = userDataCache;
        this.buttonHandler = buttonHandler;
    }

    public BotApiMethod<?> handleUpdate(Update update) {
        SendMessage replyMessage = null;

        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            log.info("New callbackQuery from User: {}, userId: {}, with data: {}", update.getCallbackQuery().getFrom().getUserName(),
                    callbackQuery.getInlineMessageId(), update.getCallbackQuery().getData());
            int userId= callbackQuery.getFrom().getId();
            userDataCache.setUsersCurrentBotState(userId, BotState.ADD_TO_FAVORITE);
            return buttonHandler.handle(callbackQuery);
        }

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
        BotState botState;
        new SendMessage();
        SendMessage replyMessage;

        switch (inputMsg) {
            case "/start":
                botState = BotState.MAIN_MENU;
                break;
            case "Помощь":
                botState = BotState.SHOW_HELP_MENU;
                break;
           case "Начать поиск":
                botState = BotState.START_SEARCH;
                break;
            case "Смотреть избранные":
                botState = BotState.SEE_FAVORITE;
                break;
            case  "Удалить избранные":
                botState= BotState.DELETE_FAVORITE;
                break;
            case "Добавить избранные":
                botState=BotState.ADD_TO_FAVORITE;
                break;

            default:
                botState = userDataCache.getUsersCurrentBotState(userId);
                break;
        }
        userDataCache.setUsersCurrentBotState(userId, botState);
        replyMessage = botStateContext.processInputMessage(botState, message);

        return replyMessage;
    }

}
