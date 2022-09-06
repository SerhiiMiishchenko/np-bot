package org.mishchuk7.enums;

import static org.mishchuk7.enums.StatusColor.*;

public enum StatusCode {
    NOT_SEND("1", "Відправник самостійно створив цю накладну, але ще не надав до відправки", GREY),
    DELETED("2", "Видалено", RED),
    NOT_FOUND("3", "Номер не знайдено", RED),
    PARCEL_IN_THE_CITY1("4", "Відправлення у місті ХХXХ. (Статус для межобластных отправлений)", BLUE),
    PARCEL_IN_THE_CITY2("41" ,"Відправлення у місті ХХXХ. (Статус для услуг локал стандарт и локал экспресс - доставка в пределах города)", BLUE),
    PARCEL_ON_ITS_WAY_TO_THE_CITY("5", "Відправлення прямує до міста YYYY", BLUE),
    PARCEL_IN_THE_CITY3("6", "Відправлення у місті YYYY, орієнтовна доставка до ВІДДІЛЕННЯ-XXX dd-mm. Очікуйте додаткове повідомлення про прибуття", BLUE),
    ARRIVED_AT_THE_STATION("7", "Прибув на відділення", ORANGE),
    ARRIVED_AT_THE_STATION_POSHTOMAT("8", "Прибув на відділення (завантажено в Поштомат)", ORANGE),
    PARCEL_RECEIVED("9", "Відправлення отримано", GREEN),
    PARCEL_RECEIVED_CASH_TRANSFER("10", "Відправлення отримано %DateReceived%. Протягом доби ви одержите SMS-повідомлення про надходження грошового переказу та зможете отримати його в касі відділення «Нова пошта»", GREEN),
    PARCEL_RECEIVED_CASH_TRANSFER_ISSUED_TO_RECIPIENT("11", "Відправлення отримано %DateReceived%. Грошовий переказ видано одержувачу.", GREEN),
    COMPLETES_YOUR_SHIPMENT("12", "Нова Пошта комплектує ваше відправлення", GREY),
    ON_THE_WAY_TO_RECIPIENT("101", "На шляху до одержувача", BLUE),
    REFUSAL_RETURN_ORDER("102", "Відмова від отримання (Відправником створено замовлення на повернення)", RED),
    REFUSAL_RECIPIENT("103", "Відмова одержувача (отримувач відмовився від відправлення)", RED),
    CHANGED_ADDRESS("104", "Змінено адресу", GREEN),
    TERMINATED_STORAGE("105", "Припинено зберігання", RED),
    RECEIVED_RETURN_SHIPMENT("106", "Одержано і створено ЄН зворотньої доставки", GREEN),
    FAILED_DELIVERY("111", "Невдала спроба доставки через відсутність Одержувача на адресі або зв'язку з ним", ORANGE),
    DELIVERY_DATE_HAS_BEEN_RESCHEDULED("112", "Дата доставки перенесена Одержувачем", ORANGE);

    final String id;
    final String description;
    final StatusColor color;

    StatusCode(String id, String description, StatusColor color) {
        this.id = id;
        this.description = description;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public StatusColor getColor() {
        return color;
    }
}
