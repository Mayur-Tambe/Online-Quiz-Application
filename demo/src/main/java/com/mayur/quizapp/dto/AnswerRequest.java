package com.mayur.quizapp.dto;

import java.util.List;

import lombok.Data;

@Data
public class AnswerRequest {
    private long questionId;
    private List<Long> selectedOptionIds;
    private String textAnswer;
}
