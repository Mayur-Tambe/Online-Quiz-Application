package com.mayur.quizapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mayur.quizapp.model.Option;

public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findByQuestion_Id(long questionId);
}
