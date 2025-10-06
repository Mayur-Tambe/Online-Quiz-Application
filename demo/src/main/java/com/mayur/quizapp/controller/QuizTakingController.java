package com.mayur.quizapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayur.quizapp.dto.AnswerRequest;
import com.mayur.quizapp.dto.PublicQuestionResponse;
import com.mayur.quizapp.dto.SubmitResponse;
import com.mayur.quizapp.service.QuizTakingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/quizzes")
@RequiredArgsConstructor
public class QuizTakingController {
    private final QuizTakingService quizTakingService;

    // Fetch questions for quiz taker (no correct answers)
    @GetMapping("/{quizId}/questions")
    public ResponseEntity<List<PublicQuestionResponse>> getPublicQuestions(@PathVariable Long quizId) {
        return ResponseEntity.ok(quizTakingService.getPublicQuestions(quizId));
    }

    // Submit answers and get score
    @PostMapping("/{quizId}/submit")
    public ResponseEntity<SubmitResponse> submitAnswers(@PathVariable Long quizId,
                                                        @RequestBody List<AnswerRequest> answers) {
        SubmitResponse res = quizTakingService.submitAnswers(quizId, answers);
        return ResponseEntity.ok(res);
    }
}
