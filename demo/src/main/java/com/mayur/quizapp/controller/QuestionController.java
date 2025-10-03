package com.mayur.quizapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayur.quizapp.dto.CreateQuestionRequest;
import com.mayur.quizapp.dto.QuestionResponse;
import com.mayur.quizapp.service.QuestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {
private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionResponse> addQuestion(@RequestBody CreateQuestionRequest request){
        return ResponseEntity.ok(questionService.addQuestion(request));
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<QuestionResponse>> getQuestionsByQuiz(@PathVariable long quizId){
        return ResponseEntity.ok(questionService.getQuestionsByQuiz(quizId));
    }
}
