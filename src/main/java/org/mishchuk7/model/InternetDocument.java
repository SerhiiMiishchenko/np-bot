package org.mishchuk7.model;

import lombok.Builder;
import lombok.Data;
import org.mishchuk7.enums.CargoType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@Builder
public class InternetDocument {

    private String dateTime;
    private String counterpartyRecipientDescription;
    private String documentWeight;
    private String arrivalDateTime;
    private String counterpartySenderDescription;
    private String cargoDescription;
    private String citySenderDescription;
    private String cargoType;
    private String payerType;
    private String senderName;
    private String seatsAmount;
    private String trackingStatusCode;
    private String phoneRecipient;
    private String recipientAddressDescription;
    private String number;
    private String phoneSender;
    private String recipientName;
    private String scanSheetInternetNumber;
    private String senderAddressDescription;
    private String trackingStatusName;

    @Override
    public String toString() {
        return "<b>Посилка:</b>\n" + number +
                "\n<b>Дата створення:</b>\n" + getDate(dateTime) +
                "\n<b>Статус:</b>\n" + trackingStatusName +
                "\n<b>Тип відправлення:</b>\n" + CargoType.getDescriptionFromInput(cargoType) +
                "\n<b>Кількість місць:</b>\n" + seatsAmount +
                "\n<b>Вага:</b>\n" + documentWeight +
                "\n<b>Від:</b>\n" + counterpartySenderDescription +
                "\n" + senderName +
                "\n" + phoneSender +
                "\n" + senderAddressDescription +
                "\n<b>Адреса доставки:</b>\n" + recipientAddressDescription +
                "\n<b>Отримувач:</b>\n" + counterpartyRecipientDescription +
                "\n" + recipientName +
                "\n" + phoneRecipient + "\n" +
                "-".repeat(25) +
                "\n";
    }

    private String getDate(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(dateTime.substring(0, 10));
        return date.format(formatter);
    }
}
