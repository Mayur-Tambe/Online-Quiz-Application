package com.mayur.quizapp.dto;

import com.mayur.quizapp.model.QuestionType;

import lombok.Data;

@Data
public class CreateQuestionRequest {
    private long quizId;
    private String text;
    private QuestionType type;
}
