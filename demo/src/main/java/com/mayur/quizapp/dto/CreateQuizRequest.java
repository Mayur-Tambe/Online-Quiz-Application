package com.mayur.quizapp.dto;
import lombok.Data;

@Data
public class CreateQuizRequest {
    private String title;
    private String description;
}
