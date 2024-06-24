package com.app.backend.business.services;

import java.util.List;
import java.util.Optional;

import com.app.backend.dao.entities.Question;

public interface QuestionService {
    Question createQuestion(Question question);
    List<Question> getAllQuestions();
    Optional<Question> getQuestionById(Long id);
    Optional<Question> updateQuestion(Long id, Question question);
    boolean deleteQuestion(Long id);
    void deleteAllQuestions();
}
