package org.mishchuk7.enums;

/**
 * Possible cargo types.
 * @author Serhii Mishchenko
* */
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

    /**
     *Parses the cargo description from the API response.
     *
     * @param response from the API
     * @return cargo type description
     */
    public static String getDescriptionFromInput(String response) {
        for (CargoType type : values()) {
            if (response.equalsIgnoreCase(type.toString()))
                return type.getDescription();
        }
        return response;
    }
}
