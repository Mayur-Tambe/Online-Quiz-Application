package com.mayur.quizapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mayur.quizapp.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByQuizId(long quizId);

    // fetch questions + options in one query
    @Query("select q from Question q left join fetch q.options where q.quiz.id = :quizId")
    List<Question> findByQuizIdWithOptions(@Param("quizId") Long quizId);
}
