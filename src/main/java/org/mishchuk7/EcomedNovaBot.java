package org.mishchuk7;

import lombok.extern.slf4j.Slf4j;
import org.mishchuk7.model.InternetDocument;
import org.mishchuk7.model.InternetDocumentRequest;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.List;

import static org.mishchuk7.constants.Constants.BOT_NAME;
import static org.mishchuk7.constants.Constants.BOT_TOKEN;

@Slf4j
@Component
public class EcomedNovaBot extends TelegramLongPollingBot {
    private final InternetDocumentRequest request;

    @Autowired
    public EcomedNovaBot () {
        this.request = new InternetDocumentRequest();
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String findByString = "";
        List<InternetDocument> internetDocuments;
        String result = "";
        SendMessage message;

        if (update.hasMessage() && update.getMessage().hasText()) {
            String textFromUser = update.getMessage().getText();
            Long userId = update.getMessage().getChatId();
            Long chatId = update.getMessage().getChatId();
            String userFirstName = update.getMessage().getFrom().getFirstName();
            log.info("[{}, {}] : {}", userId, userFirstName, textFromUser);

            try {
                findByString = request.getUserInput(textFromUser);
                internetDocuments = request.findDocumentByData(findByString);
                result = internetDocuments.stream()
                        .map(InternetDocument::toString)
                        .reduce((doc, doc1) -> doc + "\n" + doc1)
                        .orElse("Відправлень не знайдено.");
                message = SendMessage.builder()
                        .chatId(chatId)
                        .text(result)
                        .build();
                message.getEntities();
                this.sendApiMethod(message);
            } catch (IOException | InterruptedException | TelegramApiException e) {
                log.error("Exception when sending message: ", e);
            }
        } else {
            log.warn("Unexpected update from user");
        }
    }
}
