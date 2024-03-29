package org.mishchuk7.enums;

import lombok.Getter;

@Getter
public enum Fields {
    SCHEDULED_DELIVERY_DATE("ScheduledDeliveryDate"),
    FIND_BY_DATA("FindByData"),
    PAGE("Page"),
    LIMIT("Limit"),
    DATE_FROM("DateFrom"),
    DATE_TO("DateTo"),
    DATE_TIME("DateTime"),
    COUNTERPARTY_RECIPIENT_DESCRIPTION("CounterpartyRecipientDescription"),
    DOCUMENT_WEIGHT("DocumentWeight"),
    ARRIVAL_DATE_TIME("ArrivalDateTime"),
    COUNTERPARTY_SENDER_DESCRIPTION("CounterpartySenderDescription"),
    CARGO_DESCRIPTION("CargoDescription"),
    CITY_SENDER_DESCRIPTION("CitySenderDescription"),
    CARGO_TYPE("CargoType"),
    PAYER_TYPE("PayerType"),
    SENDER_NAME("SenderName"),
    SEATS_AMOUNT("SeatsAmount"),
    TRACKING_STATUS_CODE("TrackingStatusCode"),
    PHONE_RECIPIENT("PhoneRecipient"),
    RECIPIENT_ADDRESS_DESCRIPTION("RecipientAddressDescription"),
    NUMBER("Number"),
    PHONE_SENDER("PhoneSender"),
    RECIPIENT_NAME("RecipientName"),
    SCAN_SHEET_INTERNET_NUMBER("ScanSheetInternetNumber"),
    SENDER_ADDRESS_DESCRIPTION("SenderAddressDescription"),
    TRACKING_STATUS_NAME("TrackingStatusName"),
    DOCUMENT_COST("DocumentCost");

    private final String field;

    Fields(String field) {
        this.field = field;
    }

}
