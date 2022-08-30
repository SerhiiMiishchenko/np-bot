package org.mishchuk7.http;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class HttpRequestBuilder {
    private String apiKey;
    private String modelName;
    private String calledMethod;
    private Map<String, String> methodProperties;
}
