package com.mayur.quizapp.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mayur.quizapp.dto.CreateQuizRequest;
import com.mayur.quizapp.dto.QuizResponse;
import com.mayur.quizapp.model.Quiz;
import com.mayur.quizapp.repository.QuizRepository;
import com.mayur.quizapp.service.QuizService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    @Override
    public QuizResponse createQuiz(CreateQuizRequest request){
        
        if(quizRepository.existsByTitle(request.getTitle())){
            throw new RuntimeException("Quiz with title already exist" + request.getTitle());
        }
        
        Quiz quiz = Quiz.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .build();

        Quiz savedQuiz = quizRepository.save(quiz);

        return toResponse(savedQuiz);
    }

    @Override
    public List<QuizResponse> getAllQuizzes() {
        return quizRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public QuizResponse getQuizById(long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + id));
        return toResponse(quiz);
    }

    @Override
    public void deleteQuiz(long id) {
        if(!quizRepository.existsById(id)){
            throw new RuntimeException("Quiz not found with id: " + id);
        }
        quizRepository.deleteById(id);
    }

    private QuizResponse toResponse(Quiz quiz) {
        return QuizResponse.builder()
                .id(quiz.getId())
                .title(quiz.getTitle())
                .description(quiz.getDescription())
                .createdAt(quiz.getCreatedAt())
                .build();
    }
    
}
