package org.mishchuk7.enums;

public enum PayerType {
    SENDER("Sender", "Відправник"),
    RECIPIENT("Recipient", "Одержувач"),
    THIRD_PERSON("ThirdPerson", "Третя особа");

    private final String ref;
    private final String description;

    PayerType(String ref, String description) {
        this.ref = ref;
        this.description = description;
    }

    public String getRef() {
        return ref;
    }

    public String getDescription() {
        return description;
    }

    public static String descriptionOf(String type) {
        for (PayerType payerType : values()) {
            if (payerType.getRef().equalsIgnoreCase(type)) return payerType.getDescription();
        }
        return "";
    }
}
