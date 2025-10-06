package com.mayur.quizapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubmitResponse {
    private int score;
    private int total;
}
