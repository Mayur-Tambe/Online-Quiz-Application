package com.mayur.quizapp.dto;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class QuizResponse {

    private long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
}
