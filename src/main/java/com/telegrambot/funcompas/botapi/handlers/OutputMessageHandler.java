package com.telegrambot.funcompas.botapi.handlers;

import com.telegrambot.funcompas.botapi.BotState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface OutputMessageHandler {
    SendMessage handle(SendMessage message, int userId, BotState state);

    BotState getHandlerName();
}

