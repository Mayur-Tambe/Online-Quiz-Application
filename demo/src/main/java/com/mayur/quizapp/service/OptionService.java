package com.mayur.quizapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mayur.quizapp.model.Option;
import com.mayur.quizapp.repository.OptionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OptionService {
    private final OptionRepository optionRepository;
    
    public List<Option> getOptionsByQuestionId(long questionId) {
        return optionRepository.findByQuestion_Id(questionId);
    }

    public Option addOption(Option option){
        return optionRepository.save(option);
    }

}
