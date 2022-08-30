package org.mishchuk7.enums;

public enum Fields {
    DESCRIPTION("Description"),
    REF("Ref"),
    CITY("City"),
    COUNTERPARTY("Counterparty"),
    FIRST_NAME("FirstName"),
    LAST_NAME("LastName"),
    MIDDLE_NAME("MiddleName"),
    OWNER_SHIP_FORM_DESCRIPTION("OwnershipFormDescription"),
    EDRPOU("EDRPOU"),
    COUNTERPARTY_TYPE("CounterpartyType"),
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
    TRACKING_STATUS_NAME("TrackingStatusName");

    private final String field;

    Fields(String field) {
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
