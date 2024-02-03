package org.mishchuk7.model;

import lombok.Builder;
import lombok.Data;
import org.mishchuk7.enums.CargoType;
import org.mishchuk7.enums.PayerType;
import org.mishchuk7.enums.StatusCode;
import org.mishchuk7.enums.StatusColor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

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
    private String scheduledDeliveryDate;
    private String documentCost;

    @Override
    public String toString() {
        StatusColor color = getStatusColor(trackingStatusCode);
        String date = getDateAndTime(makeDateByColor(color));
        String description = makeDescriptionByColor(color);
        String payer = Arrays.stream(PayerType.values())
                .filter(p -> p.getRef().equalsIgnoreCase(payerType))
                .map(PayerType::getDescription)
                .findFirst()
                .orElse("Unknown payer type");

        return "<b>Посилка:</b> " + "<u>" + number + "</u>" +
                "\n<b>Дата створення:</b> " + getDateAndTime(dateTime) +
                "\n<b>Статус:</b> " + trackingStatusName + getStatusColor(trackingStatusCode).getColor() +
                "\n<b>" + description + "</b>\n" + date +
                "\n<b>Тип відправлення:</b> " + CargoType.getDescriptionFromInput(cargoType) +
                "\n<b>Кількість місць:</b> " + seatsAmount +
                "\n<b>Вага:</b> " + documentWeight +
                "\n<b>Від:</b>\n" + counterpartySenderDescription +
                "\n" + senderName +
                "\n" + phoneSender +
                "\n" + senderAddressDescription +
                "\n<b>Адреса доставки:</b>\n" + recipientAddressDescription +
                "\n<b>Отримувач:</b>\n" + counterpartyRecipientDescription +
                "\n" + recipientName +
                "\n" + phoneRecipient +
                "\n<b>Вартість доставки:</b> " + documentCost + " грн" +
                "\n<b>Платник за доставку:</b> " + payer;
    }

    private String makeDescriptionByColor(StatusColor color) {
        String description;
        switch (color) {
            case GREEN:
                description = "Дата отримання: ";
                break;
            case ORANGE:
            case RED:
                description = "Дата прибуття: ";
                break;
            case BLUE:
            case GREY:
            default:
                description = "Орієнтовна дата прибуття: ";
                break;
        }
        return description;
    }

    private String makeDateByColor(StatusColor color) {
        String date;
        switch (color) {
            case ORANGE:
            case RED:
            case GREEN:
                date = arrivalDateTime;
                break;
            case BLUE:
            case GREY:
            default:
                date = scheduledDeliveryDate;
                break;
        }
        return date;
    }

    private StatusColor getStatusColor(String statusCode) {
        StatusColor color = StatusColor.GREY;
        for (StatusCode code : StatusCode.values()) {
            if (code.getId().equalsIgnoreCase(statusCode)) {
                color = code.getColor();
            }
        }
        return color;
    }

    private String getDateAndTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date = LocalDateTime.parse(dateTime, formatter);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return date.format(formatter2);
    }

}
