package com.aedemirsen.methodnamegenerator;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Request {
    private String model;
    private List<Message> messages;
}
