package com.mayur.quizapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mayur.quizapp.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByQuizId(long quizId);
}
