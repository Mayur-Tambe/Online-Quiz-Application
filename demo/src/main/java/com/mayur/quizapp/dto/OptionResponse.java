package com.mayur.quizapp.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptionResponse {
    private long id;
    private String text;
    private boolean correct;
}
