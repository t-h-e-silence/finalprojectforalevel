package com.telegrambot.funcompas.appconfig;

import com.telegrambot.funcompas.botapi.TelegramFacade;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
    private String webHookPath;
    private String botUserName;
    private String botToken;

    public BotConfig() {
    }

    @Bean
    public TelegramBot telegramBot(TelegramFacade telegramFacade) {
        TelegramBot telegramBot = new TelegramBot(telegramFacade);
        telegramBot.setBotUserName(botUserName);
        telegramBot.setBotToken(botToken);
        telegramBot.setWebHookPath(webHookPath);

        return telegramBot;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:commands");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
