package com.telegrambot.funcompas;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.meta.generics.WebhookBot;
import org.telegram.telegrambots.starter.TelegramBotInitializer;
import org.telegram.telegrambots.starter.TelegramBotStarterConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


@SpringBootTest
class FuncompasApplicationTests {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(MockTelegramBotsApi.class, TelegramBotStarterConfiguration.class));

    @Test
    void createMockTelegramBotsApiWithDefaultSettings() {
        this.contextRunner.run((context) -> {
            assertThat(context).hasSingleBean(TelegramBotsApi.class);
            assertThat(context).doesNotHaveBean(WebhookBot.class);
            verifyNoMoreInteractions(context.getBean(TelegramBotsApi.class));
        });
    }


    @Test
    void createOnlyWebhookBot() {
        this.contextRunner.withUserConfiguration(WebhookBotConfig.class)
                .run((context) -> {
                    assertThat(context).hasSingleBean(WebhookBot.class);
                    assertThat(context).doesNotHaveBean(LongPollingBot.class);

                    TelegramBotsApi telegramBotsApi = context.getBean(TelegramBotsApi.class);

                    verify(telegramBotsApi, times(1)).registerBot( context.getBean(WebhookBot.class) );
                    verifyNoMoreInteractions(telegramBotsApi);
                });
    }
    @Configuration
    static class MockTelegramBotsApi{

        @Bean
        public TelegramBotsApi telegramBotsApi() {
            return mock(TelegramBotsApi.class);
        }
    }


    @Configuration
    static class WebhookBotConfig{
        @Bean
        public WebhookBot webhookBot() {
            return mock(WebhookBot.class);
        }
    }
}
