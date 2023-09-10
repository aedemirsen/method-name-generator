package com.aedemirsen.methodnamegenerator.constants;

public class RestConstants {

    public static final String GENERATE_METHOD_NAME = "/generate-method-name";
    public static final String REQUEST_SENTENCE =
            "Correct the existing method name or generate " +
            "the most suitable method name for the code below" +
            "(I want only the method name):\n%s";
    public static final String REQUEST_ROLE = "user";
    public static final String FINAL_RESPONSE_PATH = "/choices/0/message/content";
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER_TOKEN = "Bearer %s";

}
