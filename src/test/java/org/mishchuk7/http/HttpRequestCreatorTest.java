package org.mishchuk7.http;

import org.junit.jupiter.api.Test;
import org.mishchuk7.constants.Constants;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HttpRequestCreatorTest {
    private final HttpRequestCreator httpRequestCreator = new HttpRequestCreator();
    private final String post = String.format("" +
            "{\n" +
            "   \"apiKey\": \"%s\",\n" +
            "   \"modelName\": \"Counterparty\",\n" +
            "   \"calledMethod\": \"getCounterparties\",\n" +
            "   \"methodProperties\": {\n" +
            "\"CounterpartyProperty\" : \"Sender\",\n" +
            "\"Page\" : \"1\"\n" +
            "   }\n" +
            "}", Constants.NP_API_KEY);

    @Test
    public void testStatusCodeFromResponse() throws IOException, InterruptedException {
        int actual = httpRequestCreator.getStatusCodeFromResponse(post);
        int expected = 200;
        assertEquals(expected, actual);
    }

    @Test
    public void testBodyFromResponse() throws IOException, InterruptedException {
        String actual = httpRequestCreator.getBodyFromResponse(post);
        String response = "{\"success\":true,\"data\":[{\"Description\":\"\\u0422\\u041e\\u0412 \\\"\\u0415\\u041a\\u041e\\u041c\\u0415\\u0414\\\"\",\"Ref\":\"29391d40-3c56-11ea-9937-005056881c6b\",\"City\":\"00000000-0000-0000-0000-000000000000\",\"Counterparty\":\"9a55444f-3a21-11dd-90d9-001a92567626\",\"FirstName\":\"\",\"LastName\":\"\",\"MiddleName\":\"\",\"CounterpartyFullName\":\"\\u0422\\u041e\\u0412\\u0410\\u0420\\u0418\\u0421\\u0422\\u0412\\u041e \\u0417 \\u041e\\u0411\\u041c\\u0415\\u0416\\u0415\\u041d\\u041e\\u042e \\u0412\\u0406\\u0414\\u041f\\u041e\\u0412\\u0406\\u0414\\u0410\\u041b\\u042c\\u041d\\u0406\\u0421\\u0422\\u042e \\\"\\u0415\\u041a\\u041e\\u041c\\u0415\\u0414\\\"\",\"OwnershipFormRef\":\"7f0f351d-2519-11df-be9a-000c291af1b3\",\"OwnershipFormDescription\":\"\\u0422\\u041e\\u0412\",\"EDRPOU\":\"22922786\",\"CounterpartyType\":\"Organization\",\"TrustedCounterpartyType\":\"\",\"CityDescription\":\"\"}],\"errors\":[],\"warnings\":[],\"info\":{\"totalCount\":1},\"messageCodes\":[],\"errorCodes\":[],\"warningCodes\":[],\"infoCodes\":[]}";
        assertEquals(response, actual);
    }
}