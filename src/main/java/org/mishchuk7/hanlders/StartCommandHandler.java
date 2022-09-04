package org.mishchuk7.hanlders;

import org.mishchuk7.model.UserRequest;
import org.mishchuk7.service.TelegramService;

public class StartCommandHandler extends UserRequestHandler{

    private static final String command = "/start";

    private final TelegramService telegramService;

    public StartCommandHandler(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isCommand(userRequest.getUpdate(), command);
    }

    @Override
    public void handle(UserRequest dispatchRequest) {
        telegramService.sendMessage(dispatchRequest.getChatId(),
                "Для пошуку відправлення введіть номер телефону отримувача або відправника.\n" +
                        "Також пошук здійснюється за прізвищем отримувача або відправника, або за номером відправлення." );
    }

    @Override
    public boolean isGlobal() {
        return true;
    }
}
