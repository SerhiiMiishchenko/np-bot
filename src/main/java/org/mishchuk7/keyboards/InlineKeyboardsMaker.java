package org.mishchuk7.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static org.mishchuk7.enums.KeyboardButtons.ONE_WAYBILL;
import static org.mishchuk7.enums.KeyboardButtons.ALL_WAYBILLS;

public class InlineKeyboardsMaker {

    public static InlineKeyboardMarkup getInlineKeyboardMarkup(String textFromUser) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardButton oneWaybill = createButton(ONE_WAYBILL.getButtonText(), textFromUser + ":" + ONE_WAYBILL.getButtonText());
        InlineKeyboardButton allWaybills = createButton(ALL_WAYBILLS.getButtonText(), textFromUser+ ":" + ALL_WAYBILLS.getButtonText());
        buttons.add(oneWaybill);
        buttons.add(allWaybills);
        keyboard.add(buttons);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    private static InlineKeyboardButton createButton(String buttonName, String buttonCallbackData) {
        InlineKeyboardButton button = new InlineKeyboardButton(buttonName);
        button.setCallbackData(buttonCallbackData);
        return button;
    }
}
