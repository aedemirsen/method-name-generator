package com.aedemirsen.methodnamegenerator;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Prompt {

    private String methodContent;
}
