package org.mishchuk7.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.mishchuk7.enums.KeyboardButtons.ONE_WAYBILL;
import static org.mishchuk7.enums.KeyboardButtons.ALL_WAYBILLS;

public class InlineKeyboardsMaker {

    private final InlineKeyboardMarkup inlineKeyboardMarkup;
    private final List<List<InlineKeyboardButton>> keyboard;
    private final List<InlineKeyboardButton> buttons;

    public InlineKeyboardsMaker() {
        this.inlineKeyboardMarkup = new InlineKeyboardMarkup();
        this.keyboard = new ArrayList<>();
        this.buttons = new ArrayList<>();
    }

    public InlineKeyboardMarkup getInlineKeyboardMarkup() {
        InlineKeyboardButton oneWaybill = createButton("Останнє відправлення", ONE_WAYBILL.getButtonText());
        InlineKeyboardButton allWaybills = createButton("Усі відправлення", ALL_WAYBILLS.getButtonText());
        buttons.add(oneWaybill);
        buttons.add(allWaybills);
        keyboard.add(buttons);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    private InlineKeyboardButton createButton(String buttonName, String buttonCallbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton(buttonName);
        button.setCallbackData(buttonCallbackData);
        return button;
    }
}
