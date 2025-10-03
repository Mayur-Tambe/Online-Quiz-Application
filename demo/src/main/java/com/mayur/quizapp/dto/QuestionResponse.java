package com.mayur.quizapp.dto;

import java.time.LocalDateTime;

import com.mayur.quizapp.model.QuestionType;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionResponse {
    private long id;
    private long quizId;
    private String text;
    private QuestionType type;
    private LocalDateTime createdAt;
}
