package com.app.backend.business.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.backend.business.services.QuestionService;
import com.app.backend.dao.entities.Question;
import com.app.backend.dao.repositories.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Optional<Question> updateQuestion(Long id, Question question) {
        return questionRepository.findById(id).map(existingQuestion -> {
            existingQuestion.setDate(question.getDate());
            existingQuestion.setUrgent(question.isUrgent());
            existingQuestion.setTexte(question.getTexte());
            existingQuestion.setPatient(question.getPatient());
            return questionRepository.save(existingQuestion);
        });
    }

    @Override
    public boolean deleteQuestion(Long id) {
        return questionRepository.findById(id).map(question -> {
            questionRepository.delete(question);
            return true;
        }).orElse(false);
    }

    @Override
    public void deleteAllQuestions() {
        questionRepository.deleteAll();
    }
}
