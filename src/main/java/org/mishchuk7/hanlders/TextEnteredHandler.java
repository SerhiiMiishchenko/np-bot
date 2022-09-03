package org.mishchuk7.hanlders;

import org.mishchuk7.keyboards.InlineKeyboardsMaker;
import org.mishchuk7.model.UserRequest;
import org.mishchuk7.service.TelegramService;

public class TextEnteredHandler extends UserRequestHandler{
    private final TelegramService telegramService;

    public TextEnteredHandler(TelegramService telegramService) {
        this.telegramService = telegramService;
    }

    @Override
    public boolean isApplicable(UserRequest userRequest) {
        return isTextMessage(userRequest.getUpdate(), userRequest.getUpdate().getMessage().getText());
    }

    @Override
    public void handle(UserRequest userRequest) {
        telegramService.sendMessage(userRequest.getChatId(),
                "Оберіть варіант звіту ⤵",
                InlineKeyboardsMaker.getInlineKeyboardMarkup(userRequest.getUpdate().getMessage().getText()));
    }

    @Override
    public boolean isGlobal() {
        return false;
    }
}
