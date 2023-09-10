package com.aedemirsen.methodnamegenerator;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.aedemirsen.methodnamegenerator.RestConstants.*;

@Service
public class GptService {

    @Value("${method-name-generator.api-key}")
    private String apiKey;

    @Value("${method-name-generator.api-url}")
    private String apiUrl;

    @Value("${method-name-generator.gpt-model}")
    private String gptModel;

    @SneakyThrows
    public ResponseEntity<String> generateText(String prompt) {
        if(apiKey.isEmpty() || apiUrl.isEmpty() || gptModel.isEmpty()){
            throw new Exception(ExceptionConstants.ENVIRONMENT_VAR_ERROR);
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(AUTHORIZATION, BEARER_TOKEN.formatted(apiKey));

        String body = REQUEST_SENTENCE.formatted(prompt);

        Request request = Request.builder()
                .model(gptModel)
                .messages(List.of(Message.builder().role(REQUEST_ROLE).content(body).build()))
                .build();

        HttpEntity<Request> entity = new HttpEntity<>(request, headers);

        return restTemplate.exchange(apiUrl, HttpMethod.POST, entity, String.class);
    }
}

