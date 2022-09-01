package org.mishchuk7.enums;

public enum KeyboardButtons {
    ONE_WAYBILL("last waybill"),
    ALL_WAYBILLS("all waybills");

    final String buttonText;

    KeyboardButtons(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getButtonText() {
        return buttonText;
    }
}
