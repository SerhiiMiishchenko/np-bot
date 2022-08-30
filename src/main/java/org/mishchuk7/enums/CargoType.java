package org.mishchuk7.enums;

public enum CargoType {
    PARCEL("Посилка"),
    CARGO("Вантаж"),
    DOCUMENTS("Документи"),
    TIRES_WHEELS("Шини-диски"),
    PALLET("Палети");

    final String description;

    CargoType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static String getDescriptionFromInput(String input) {
        for (CargoType type : values()) {
            if (input.equalsIgnoreCase(type.toString()))
                return type.getDescription();
        }
        return input;
    }
}
