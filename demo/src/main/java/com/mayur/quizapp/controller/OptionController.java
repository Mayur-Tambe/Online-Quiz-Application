package com.mayur.quizapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayur.quizapp.model.Option;
import com.mayur.quizapp.service.OptionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/options")
@RequiredArgsConstructor
public class OptionController {
    private final OptionService optionService;

    @PostMapping
    public ResponseEntity<Option> addOption(@RequestBody Option option) {
        Option savedOption = optionService.addOption(option);
        return ResponseEntity.ok(savedOption);
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<Option>> getOptionsByQuestionId(@PathVariable long questionId) {
        return ResponseEntity.ok(optionService.getOptionsByQuestionId(questionId));
    }
}
