package org.mishchuk7;

import lombok.extern.slf4j.Slf4j;
import org.mishchuk7.hanlders.CallbackQueryHandler;
import org.mishchuk7.hanlders.StartCommandHandler;
import org.mishchuk7.hanlders.TextEnteredHandler;
import org.mishchuk7.hanlders.UserRequestHandler;
import org.mishchuk7.sender.EcomedNovaBotSender;
import org.mishchuk7.service.TelegramService;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Application {
    private static final Dispatcher dispatcher = new Dispatcher(getHandlers());

    public static void main(String[] args) throws TelegramApiException {

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            log.info("Registering bot...");
            telegramBotsApi.registerBot(new EcomedNovaBot(dispatcher));
        } catch (TelegramApiRequestException e) {
            log.error("Failed to register bot(check internet connection / bot token or make sure only one instance of bot is running).", e);
        }
        log.info("Telegram bot is ready to accept updates from user......");
    }

    private static List<UserRequestHandler> getHandlers() {
        List<UserRequestHandler> handlers = new ArrayList<>();
        TelegramService telegramService = new TelegramService(new EcomedNovaBotSender());
        handlers.add(new StartCommandHandler(telegramService));
        handlers.add(new CallbackQueryHandler(telegramService));
        handlers.add(new TextEnteredHandler(telegramService));
        return handlers;
    }

}
