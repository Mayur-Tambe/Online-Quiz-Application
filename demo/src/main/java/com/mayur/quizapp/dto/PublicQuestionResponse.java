package com.mayur.quizapp.dto;

import java.util.List;

import com.mayur.quizapp.model.QuestionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicQuestionResponse {
    private Long id;
    private String text;
    private QuestionType type;
    private List<PublicOptionResponse> options;
}
