package org.mishchuk7.enums;

public enum StatusColor {
    BLUE("\uD83D\uDD35"),
    GREEN("\uD83D\uDFE2"),
    ORANGE("\uD83D\uDFE0"),
    GREY("⚫"),
    RED("\uD83D\uDD34");

    final String color;

    StatusColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
