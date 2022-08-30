package org.mishchuk7.constants;

public class Constants {
    public static final String BASE_URL = "https://api.novaposhta.ua/v2.0/json/";
    public static final String BOT_NAME = System.getenv("BOT_NAME");
    public static final String BOT_TOKEN = System.getenv("BOT_TOKEN");
    public static final String API_KEY = System.getenv("NP_API_KEY");
    public static final String MODEL_NAME = "InternetDocument";
    public static final String CALLED_METHOD = "findDocumentByData";
}
