package com.telegrambot.funcompas.controller;


import com.telegrambot.funcompas.appconfig.TelegramBot;
import org.springframework.web.bind.annotation.*;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class WebhookController {
    private final TelegramBot telegramBot;
    public WebhookController(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

   @RequestMapping(value = "/", method = RequestMethod.POST)
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return telegramBot.onWebhookUpdateReceived(update);
    }
}