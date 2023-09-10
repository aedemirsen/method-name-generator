package com.aedemirsen.methodnamegenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.aedemirsen.methodnamegenerator.RestConstants.REQUEST_ROLE;

public class GptServiceTest {

    @Test
    public void testGenerateText() {
        String prompt = "How Are You Today?";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String apiKey = ""; //YOUR OPEN AI API KEY

        if(apiKey.equals("")){
            return;
        }

        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);

        String gptModel = "gpt-3.5-turbo";
        Request request = Request.builder()
                .model(gptModel)
                .messages(List.of(Message.builder().role(REQUEST_ROLE).content(prompt).build()))
                .build();

        HttpEntity<Request> entity = new HttpEntity<>(request, headers);

        String apiUrl = "https://api.openai.com/v1/chat/completions";
        ResponseEntity<String> response = new RestTemplate().exchange(apiUrl, HttpMethod.POST, entity, String.class);

        Assertions.assertEquals(HttpStatusCode.valueOf(200),response.getStatusCode());
    }
}
