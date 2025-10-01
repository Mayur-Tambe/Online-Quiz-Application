package com.mayur.quizapp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mayur.quizapp.model.Quiz;

public interface  QuizRepository extends JpaRepository<Quiz, Long> {
    boolean existsByTitle(String title);
}
