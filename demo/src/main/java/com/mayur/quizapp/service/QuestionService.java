package com.mayur.quizapp.service;

import java.util.List;

import com.mayur.quizapp.dto.CreateQuestionRequest;
import com.mayur.quizapp.dto.QuestionResponse;

public interface QuestionService {
    QuestionResponse addQuestion(CreateQuestionRequest request);
    List<QuestionResponse> getQuestionsByQuiz(long quizId);
}
