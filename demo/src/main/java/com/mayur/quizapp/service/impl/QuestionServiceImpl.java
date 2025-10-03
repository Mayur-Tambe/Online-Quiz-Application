package com.mayur.quizapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mayur.quizapp.dto.CreateQuestionRequest;
import com.mayur.quizapp.dto.QuestionResponse;
import com.mayur.quizapp.model.Question;
import com.mayur.quizapp.model.Quiz;
import com.mayur.quizapp.repository.QuestionRepository;
import com.mayur.quizapp.repository.QuizRepository;
import com.mayur.quizapp.service.QuestionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    @Override
    public QuestionResponse addQuestion(CreateQuestionRequest request) {
        Quiz quiz = quizRepository.findById(request.getQuizId())
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + request.getQuizId()));

        Question question = Question.builder()
                .quiz(quiz)
                .text(request.getText())
                .type(request.getType())
                .build();
        
        Question savedQuestion = questionRepository.save(question);
        return toResponse(savedQuestion);
    }

    @Override
    public List<QuestionResponse> getQuestionsByQuiz(long quizId) {
        return questionRepository.findByQuizId(quizId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private QuestionResponse toResponse(Question question) {
        return QuestionResponse.builder()
                .id(question.getId())
                .quizId(question.getQuiz().getId())
                .text(question.getText())
                .type(question.getType())
                .createdAt(question.getCreatedAt())
                .build();
    }
}
