package com.mayur.quizapp.service;

import java.util.List;

import com.mayur.quizapp.dto.CreateQuizRequest;
import com.mayur.quizapp.dto.QuizResponse;

public interface  QuizService {
    QuizResponse createQuiz(CreateQuizRequest request);
    List<QuizResponse> getAllQuizzes();
    QuizResponse getQuizById(long id);
    void deleteQuiz(long id);
}
