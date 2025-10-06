package com.mayur.quizapp.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mayur.quizapp.dto.AnswerRequest;
import com.mayur.quizapp.dto.PublicOptionResponse;
import com.mayur.quizapp.dto.PublicQuestionResponse;
import com.mayur.quizapp.dto.SubmitResponse;
import com.mayur.quizapp.model.Option;
import com.mayur.quizapp.repository.QuestionRepository;
import com.mayur.quizapp.repository.QuizRepository;
import com.mayur.quizapp.service.QuizTakingService;
import com.mayur.quizapp.model.Question;
import com.mayur.quizapp.model.QuestionType;
import com.mayur.quizapp.model.Quiz;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class QuizTakingServiceImpl implements QuizTakingService {
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    @Override
    public List<PublicQuestionResponse> getPublicQuestions(long quizId) {
        if(!quizRepository.existsById(quizId)) {
            throw new IllegalArgumentException("Quiz with id " + quizId + " does not exist.");
        }

        List<Question> questions = questionRepository.findByQuizIdWithOptions(quizId);

        return questions.stream()
                .map(this::toPublicQuestionResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SubmitResponse submitAnswers(long quizId, List<AnswerRequest> answers){
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new NoSuchElementException("Quiz not found with id: " + quizId));
        
        List<Question> questions = questionRepository.findByQuizIdWithOptions(quizId);
        
        // map answers by questionId for quick lookup
        Map<Long, AnswerRequest> answerMap = new HashMap<>();
        if (answers != null) {
            for (AnswerRequest ar : answers) {
                if (ar != null) {
                    answerMap.put(ar.getQuestionId(), ar);
                }
            }
        }

        int total = questions.size();
        int score = 0;

        for (Question q : questions) {
            AnswerRequest submitted = answerMap.get(q.getId());
            if (submitted == null) {
                // unanswered -> zero points
                continue;
            }

            QuestionType type = q.getType();
            if (type == QuestionType.TEXT) {
                // For TEXT type, compare to stored correctText (if present)
                String correct = q.getCorrectText(); // your Question entity must have this field, if not adapt
                String provided = submitted.getTextAnswer() == null ? "" : submitted.getTextAnswer().trim();
                if (!provided.isEmpty() && correct != null && !correct.isBlank()) {
                    if (provided.equalsIgnoreCase(correct.trim())) {
                        score++;
                    }
                }
            } else {
                // CHOICE types
                // compute set of correct option ids
                Set<Long> correctIds = q.getOptions().stream()
                        .filter(Option::isCorrect)
                        .map(Option::getId)
                        .collect(Collectors.toSet());

                // the options that belong to this question (to validate submitted ids)
                Set<Long> validOptionIds = q.getOptions().stream()
                        .map(Option::getId)
                        .collect(Collectors.toSet());

                Set<Long> submittedIds = submitted.getSelectedOptionIds() == null
                        ? Collections.emptySet()
                        : new HashSet<>(submitted.getSelectedOptionIds());

                // ignore submitted ids that don't belong to this question
                submittedIds.retainAll(validOptionIds);

                if (type == QuestionType.SINGLE_CHOICE) {
                    // accept only single selected id equal to correctIds
                    if (submittedIds.size() == 1 && submittedIds.equals(correctIds)) {
                        score++;
                    }
                } else if (type == QuestionType.MULTIPLE_CHOICE) {
                    // require exact match of sets
                    if (!correctIds.isEmpty() && submittedIds.equals(correctIds)) {
                        score++;
                    }
                }
            }
        }

        return new SubmitResponse(score, total);
    }

    private PublicQuestionResponse toPublicQuestionResponse(Question q) {
        List<PublicOptionResponse> options = Optional.ofNullable(q.getOptions()).orElse(Collections.emptyList())
                .stream()
                .map(o -> new PublicOptionResponse(o.getId(), o.getText()))
                .collect(Collectors.toList());

        return PublicQuestionResponse.builder()
                .id(q.getId())
                .text(q.getText())
                .type(q.getType())
                .options(options)
                .build();
    }
    
}
