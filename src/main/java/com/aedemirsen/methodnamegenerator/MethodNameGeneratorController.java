package com.aedemirsen.methodnamegenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.aedemirsen.methodnamegenerator.RestConstants.FINAL_RESPONSE_PATH;

@RestController
public class MethodNameGeneratorController {

    private final GptService gptService;

    @Autowired
    public MethodNameGeneratorController(GptService gptService) {
        this.gptService = gptService;
    }


    @SneakyThrows
    @PostMapping(RestConstants.GENERATE_METHOD_NAME)
    public ResponseEntity<String> generateText(@RequestBody String prompt) {
        ResponseEntity<String> response = gptService.generateText(prompt);
        var methodName = getExtractedMethodName(response)
                .orElseThrow(() -> new Exception(RestConstants.ExceptionConstants.UNEXPECTED_ERROR));
        return ResponseEntity.ok(methodName);
    }

    @SneakyThrows
    private Optional<String> getExtractedMethodName(ResponseEntity<String> generatedResponse){
        ObjectMapper objectMapper = new ObjectMapper();
        return Optional.of(objectMapper.readTree(generatedResponse.getBody()).at(FINAL_RESPONSE_PATH).asText());
    }
}
