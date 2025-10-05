package com.mayur.quizapp.dto;

import java.util.List;

import com.mayur.quizapp.model.QuestionType;

import lombok.Data;

@Data
public class CreateQuestionRequest {
    private long quizId;
    private String text;
    private QuestionType type;
    private List<CreateOptionRequest> options;
}
