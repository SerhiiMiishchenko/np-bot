package org.mishchuk7.enums;

import lombok.Getter;

@Getter
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

}
