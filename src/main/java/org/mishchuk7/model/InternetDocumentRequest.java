package org.mishchuk7.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.mishchuk7.constants.Constants;
import org.mishchuk7.http.HttpRequestBuilder;
import org.mishchuk7.http.HttpRequestCreator;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mishchuk7.enums.Fields.*;

@Slf4j
public class InternetDocumentRequest {

    private final HttpRequestCreator requestCreator = new HttpRequestCreator();
    private final ObjectMapper mapper = new ObjectMapper();

    public static String getUserInput(String input) {
        StringBuilder digits = new StringBuilder();
        StringBuilder letters = new StringBuilder();
        var number = input.trim().toCharArray();
        for (char ch : number) {
            if (Character.isDigit(ch)) {
                digits.append(ch);
            }
            if (Character.isLetter(ch)) {
                letters.append(ch);
            }
        }
        String result = digits.toString();
        if (result.length() == 10) {
            return "38" + result;
        }
        if (result.length() == 11) {
            return "3" + result;
        }
        return result.length() == 0 ? letters.toString() : result;
    }

    public List<InternetDocument> findDocumentByData(String findByData) throws IOException, InterruptedException {
        List<InternetDocument> documentList = new ArrayList<>();
        String post = requestCreator.getPostFromRequestBuilder(createInternetDocumentPostRequest(getMethodProperties(findByData)));
        int statusDocument = requestCreator.getStatusCodeFromResponse(post);
        log.info("status document: " + statusDocument);
        String responseBody = requestCreator.getBodyFromResponse(post);
        mapper.writerWithDefaultPrettyPrinter();
        JsonNode root = mapper.readTree(responseBody);
        JsonNode success = root.path("success");
        log.info("success: " + success.asText());
        JsonNode data = root.path("data");
        for (JsonNode n : data) {
            JsonNode result = n.path("result");
            if (result.isArray()) {
                for (JsonNode node : result) {
                    InternetDocument doc = InternetDocument.builder()
                            .dateTime(node.path(DATE_TIME.getField()).asText())
                            .counterpartyRecipientDescription(node.path(COUNTERPARTY_RECIPIENT_DESCRIPTION.getField()).asText())
                            .documentWeight(node.path(DOCUMENT_WEIGHT.getField()).asText())
                            .arrivalDateTime(node.path(ARRIVAL_DATE_TIME.getField()).asText())
                            .counterpartySenderDescription(node.path(COUNTERPARTY_SENDER_DESCRIPTION.getField()).asText())
                            .cargoDescription(node.path(CARGO_DESCRIPTION.getField()).asText())
                            .citySenderDescription(node.path(CITY_SENDER_DESCRIPTION.getField()).asText())
                            .cargoType(node.path(CARGO_TYPE.getField()).asText())
                            .payerType(node.path(PAYER_TYPE.getField()).asText())
                            .senderName(node.path(SENDER_NAME.getField()).asText())
                            .seatsAmount(node.path(SEATS_AMOUNT.getField()).asText())
                            .trackingStatusCode(node.path(TRACKING_STATUS_CODE.getField()).asText())
                            .phoneRecipient(node.path(PHONE_RECIPIENT.getField()).asText())
                            .recipientAddressDescription(node.path(RECIPIENT_ADDRESS_DESCRIPTION.getField()).asText())
                            .number(node.path(NUMBER.getField()).asText())
                            .phoneSender(node.path(PHONE_SENDER.getField()).asText())
                            .recipientName(node.path(RECIPIENT_NAME.getField()).asText())
                            .scanSheetInternetNumber(node.path(SCAN_SHEET_INTERNET_NUMBER.getField()).asText())
                            .senderAddressDescription(node.path(SENDER_ADDRESS_DESCRIPTION.getField()).asText())
                            .trackingStatusName(node.path(TRACKING_STATUS_NAME.getField()).asText())
                            .build();
                    documentList.add(doc);
                }
            }
        }
        JsonNode errors = root.path("errors");
        for (JsonNode node : errors) {
            log.info("errors: [" + node.asText() + "]");
        }
        JsonNode warnings = root.path("warnings");
        for (JsonNode node : warnings) {
            log.info("warnings: [" + node.asText() + "]");
        }
        return documentList;
    }

    private Map<String, String> getMethodProperties(String findByData) {
        String page = "0";
        String limit = "5";
        Map<String, String> map = new HashMap<>();
        map.put(FIND_BY_DATA.getField(), findByData);
        map.put(PAGE.getField(), page);
        map.put(LIMIT.getField(), limit);
        map.put(DATE_FROM.getField(), getDateFrom());
        map.put(DATE_TO.getField(), getDateTo());
        return map;
    }

    private HttpRequestBuilder createInternetDocumentPostRequest(Map<String, String> methodProperties) {
        return HttpRequestBuilder.builder()
                .apiKey(Constants.NP_API_KEY)
                .modelName(Constants.MODEL_NAME)
                .calledMethod(Constants.CALLED_METHOD)
                .methodProperties(methodProperties)
                .build();
    }

    private String getDateFrom() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime dateNow = LocalDateTime.now();
        LocalDateTime dateFrom = dateNow.minusMonths(3L);
        return dateFrom.format(formatter);
    }

    private String getDateTo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime dateNow = LocalDateTime.now();
        LocalDateTime dateFrom = dateNow.plusMonths(1L);
        return dateFrom.format(formatter);
    }
}
