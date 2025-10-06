package com.mayur.quizapp.service;

import java.util.List;

import com.mayur.quizapp.dto.AnswerRequest;
import com.mayur.quizapp.dto.PublicQuestionResponse;
import com.mayur.quizapp.dto.SubmitResponse;

public interface QuizTakingService {
    List <PublicQuestionResponse> getPublicQuestions(long quizId);
    SubmitResponse submitAnswers(long quizId, List<AnswerRequest> answers);
}
