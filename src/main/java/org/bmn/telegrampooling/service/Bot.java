package org.bmn.telegrampooling.service;

import lombok.extern.slf4j.Slf4j;
import org.bmn.telegrampooling.config.BotConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

// Аннотация @Component необходима, чтобы наш класс распознавался Spring, как полноправный Bean
@Component
@Slf4j
/**
 * Касс является основным пулом взаимодействия с Telegram
 * получение, обработка, отправка сообщений
 */
public class Bot extends TelegramLongPollingBot {

    private final BotConfig botConfig;

    @Autowired
    public Bot(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    /* Перегружаем метод интерфейса LongPollingBot
    Теперь при получении сообщения наш бот будет отвечать сообщением Hi!
     */
    @Override
    public void onUpdateReceived(Update update) {
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            sendMessage.setText("Hi!");
            execute(sendMessage);
            log.info("execute message: {}", update.getMessage());
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotUserName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }
}
