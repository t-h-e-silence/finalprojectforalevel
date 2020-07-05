package com.telegrambot.funcompas.botapi.handlers;

import com.telegrambot.funcompas.botapi.BotState;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
public interface ButtonHandler {

    BotApiMethod<?> handle(CallbackQuery buttonQuery);

    BotState getHandlerName();
}
