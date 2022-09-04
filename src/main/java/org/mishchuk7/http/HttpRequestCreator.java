package org.mishchuk7.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import static org.mishchuk7.constants.Constants.BASE_URL;

@Slf4j
public class HttpRequestCreator {
    private final HttpClient httpClient;
    private final ObjectMapper mapper;

    public HttpRequestCreator() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(20))
                .build();
        this.mapper = new ObjectMapper();
    }

    public String getPostFromRequestBuilder(HttpRequestBuilder requestBuilder) throws JsonProcessingException {
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestBuilder);
    }

    public String getBodyFromResponse(String post) throws IOException, InterruptedException {
        return getResponseFromPostRequest(post).body();
    }

    public int getStatusCodeFromResponse(String post) throws IOException, InterruptedException {
        return getResponseFromPostRequest(post).statusCode();
    }

    private HttpResponse<String> getResponseFromPostRequest(String post) throws IOException, InterruptedException {
        HttpRequest request = makePostRequest(post);
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private HttpRequest makePostRequest(String post) {
        return HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(post))
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .build();
    }
}
