package org.mishchuk7;

import lombok.extern.slf4j.Slf4j;
import org.mishchuk7.model.InternetDocumentRequest;
import org.mishchuk7.model.UserRequest;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.mishchuk7.constants.Constants.BOT_NAME;
import static org.mishchuk7.constants.Constants.BOT_TOKEN;

@Slf4j
public class EcomedNovaBot extends TelegramLongPollingBot {

    private final InternetDocumentRequest request;
    private final Dispatcher dispatcher;

    public EcomedNovaBot(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
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
        Long chatId = getUpdateId(update);
        if (update.hasMessage() && update.getMessage().hasText()) {
            String textFromUser = update.getMessage().getText();
            Long userId = update.getMessage().getChatId();
            chatId = update.getMessage().getChatId();
            String userFirstName = update.getMessage().getFrom().getFirstName();
            log.info("[{}, {}] : {}", userId, userFirstName, textFromUser);
        }
        UserRequest userRequest = UserRequest.builder()
                .update(update)
                .chatId(chatId)
                .build();

        boolean dispatched = dispatcher.dispatch(userRequest);
        if (!dispatched) {
            log.warn("Unexpected update from user");
        }
    }

    private Long getUpdateId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getChatId();
        }
        return null;
    }

}
