package com.mayur.quizapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayur.quizapp.dto.CreateQuizRequest;
import com.mayur.quizapp.dto.QuizResponse;
import com.mayur.quizapp.service.QuizService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizController {
        private final QuizService quizService;
        
        @PostMapping
        public ResponseEntity<QuizResponse> createQuiz(@RequestBody CreateQuizRequest request){
                QuizResponse createdQuiz = quizService.createQuiz(request);
                return ResponseEntity.ok(createdQuiz);
        }
        
        @GetMapping
        public ResponseEntity<List<QuizResponse>> getAllQuizzes(){
                List<QuizResponse> quizzes = quizService.getAllQuizzes();
                return ResponseEntity.ok(quizzes);
        }

        @GetMapping("/{id}")
        public ResponseEntity<QuizResponse> getQuizById(@PathVariable long id){
                QuizResponse quiz = quizService.getQuizById(id);
                return ResponseEntity.ok(quiz);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteQuiz(@PathVariable long id){
              quizService.deleteQuiz(id);
                return ResponseEntity.noContent().build();
        }
}
