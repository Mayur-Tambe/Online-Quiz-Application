package com.mayur.quizapp.dto;

import lombok.Data;

@Data
public class CreateOptionRequest {
    private String text;
    private boolean correct;
}
