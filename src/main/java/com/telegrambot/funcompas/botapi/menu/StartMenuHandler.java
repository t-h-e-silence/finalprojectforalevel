package com.telegrambot.funcompas.botapi.menu;

import com.telegrambot.funcompas.botapi.BotState;
import com.telegrambot.funcompas.botapi.handlers.InputMessageHandler;
import com.telegrambot.funcompas.service.MainMenuService;
import com.telegrambot.funcompas.service.ReplyMessagesService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class StartMenuHandler implements InputMessageHandler {

    private final MainMenuService mainMenuService;
    private final ReplyMessagesService messagesService;

    public StartMenuHandler(MainMenuService mainMenuService, ReplyMessagesService messagesService) {
        this.mainMenuService = mainMenuService;
        this.messagesService = messagesService;
    }

    @Override
    public SendMessage handle(Message message) {
        return mainMenuService.getMainMenuMessage(message.getChatId(),
                messagesService.getReplyText("reply.showMainMenu"));
    }

    @Override
    public BotState getHandlerName() {
        return BotState.MAIN_MENU;
    }
}


