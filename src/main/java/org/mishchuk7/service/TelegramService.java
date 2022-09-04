package org.mishchuk7.service;

import lombok.extern.slf4j.Slf4j;
import org.mishchuk7.sender.EcomedNovaBotSender;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
public class TelegramService {
    private final EcomedNovaBotSender ecomedNovaBotSender;

    public TelegramService(EcomedNovaBotSender ecomedNovaBotSender) {
        this.ecomedNovaBotSender = ecomedNovaBotSender;
    }

    public void sendMessage(Long chatId, String text) {
        sendMessage(chatId, text, null);
    }

    public void sendMessage(Long chatId, String text, InlineKeyboardMarkup inlineKeyboardMarkup) {
        SendMessage message = SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .parseMode(ParseMode.HTML)
                .replyMarkup(inlineKeyboardMarkup)
                .build();
        execute(message);
    }

    private void execute(BotApiMethod botApiMethod) {
        try {
            ecomedNovaBotSender.execute(botApiMethod);
        } catch (TelegramApiException e) {
            log.error("Exception: ", e);
        }
    }
}
