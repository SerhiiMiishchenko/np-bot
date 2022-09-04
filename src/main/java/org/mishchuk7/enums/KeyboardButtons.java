package org.mishchuk7.enums;

public enum KeyboardButtons {
    ONE_WAYBILL("Останнє відправлення"),
    ALL_WAYBILLS("Усі відправлення");

    final String buttonText;

    KeyboardButtons(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getButtonText() {
        return buttonText;
    }
}
