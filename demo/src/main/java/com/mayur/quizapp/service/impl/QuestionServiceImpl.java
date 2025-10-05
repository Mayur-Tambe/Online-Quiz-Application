package com.mayur.quizapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mayur.quizapp.dto.CreateQuestionRequest;
import com.mayur.quizapp.dto.OptionResponse;
import com.mayur.quizapp.dto.QuestionResponse;
import com.mayur.quizapp.model.Option;
import com.mayur.quizapp.model.Question;
import com.mayur.quizapp.model.Quiz;
import com.mayur.quizapp.repository.OptionRepository;
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
    private final OptionRepository optionRepository;

    @Override
    public QuestionResponse addQuestion(CreateQuestionRequest request) {
    //Find quiz or throw error
    Quiz quiz = quizRepository.findById(request.getQuizId())
            .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + request.getQuizId()));

    //Build and save Question
    Question question = Question.builder()
            .quiz(quiz)
            .text(request.getText())
            .type(request.getType())
            .build();

    Question savedQuestion = questionRepository.save(question);

    //Map and save Options (if any)
    List<Option> savedOptions = new ArrayList<>();
    if (request.getOptions() != null && !request.getOptions().isEmpty()) {
        List<Option> options = request.getOptions().stream().map(o -> {
            Option option = new Option();
            option.setText(o.getText());
            option.setCorrect(o.isCorrect());
            option.setQuestion(savedQuestion);
            return option;
        }).collect(Collectors.toList());

        savedOptions = optionRepository.saveAll(options);
    }

    //Build OptionResponse list
    List<OptionResponse> optionResponses = savedOptions.stream()
            .map(option -> OptionResponse.builder()
                    .id(option.getId())
                    .text(option.getText())
                    .correct(option.isCorrect())
                    .build())
            .collect(Collectors.toList());

    //Build and return QuestionResponse
    return QuestionResponse.builder()
            .id(savedQuestion.getId())
            .quizId(savedQuestion.getQuiz().getId())
            .text(savedQuestion.getText())
            .type(savedQuestion.getType())
            .createdAt(savedQuestion.getCreatedAt())
            .options(optionResponses)
            .build();
}


    @Override
    public List<QuestionResponse> getQuestionsByQuiz(long quizId) {
        return questionRepository.findByQuizId(quizId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private QuestionResponse toResponse(Question question) {
        List<OptionResponse> optionResponses = question.getOptions().stream()
        .map(option -> OptionResponse.builder()
                .id(option.getId())
                .text(option.getText())
                .correct(option.isCorrect())
                .build())
        .collect(Collectors.toList());

        return QuestionResponse.builder()
            .id(question.getId())
            .quizId(question.getQuiz().getId())
            .text(question.getText())
            .type(question.getType())
            .createdAt(question.getCreatedAt())
            .options(optionResponses)
            .build();
    }
}
