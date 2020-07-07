package com.telegrambot.funcompas.botapi;

import com.telegrambot.funcompas.botapi.handlers.InputMessageHandler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class BotStateContext {
    private final Map<BotState, InputMessageHandler> messageHandlers = new HashMap<>();

    public BotStateContext(List<InputMessageHandler> messageHandlers) {
        messageHandlers.forEach(handler -> this.messageHandlers.put(handler.getHandlerName(), handler));
    }

   public SendMessage processInputMessage(BotState currentState, Message message) {
        InputMessageHandler currentMessageHandler = findMessageHandler(currentState);
        return currentMessageHandler.handle(message);
    }


    private InputMessageHandler findMessageHandler(BotState currentState) {
        if (isFillingProfileState(currentState)) {
            return messageHandlers.get(BotState.ADD_TO_FAVORITE);
        }
        if (isDeletingProfileState(currentState)){
            return  messageHandlers.get(BotState.DELETE_FAVORITE);
        }
        return messageHandlers.get(currentState);
    }

    private boolean isDeletingProfileState(BotState currentState) {
        switch (currentState) {
            case ASK_ID:
            case DELETE_BUTTON:
                return true;
            default:
                return false;
        }
    }
    private boolean isFillingProfileState(BotState currentState) {
        switch (currentState) {
            case ASK_ID:
            case PROFILE_FILLED:
                return true;
            default:
                return false;
        }
    }
}





