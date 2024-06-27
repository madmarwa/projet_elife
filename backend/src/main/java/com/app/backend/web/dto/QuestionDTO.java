package com.app.backend.web.dto;

import java.util.Date;

import com.app.backend.dao.entities.Question;
import com.app.backend.dao.entities.User;

import lombok.Builder;

@Builder
public record QuestionDTO(
    String id,
    Date date,
    boolean urgent,
    String texte,
    User patient
) {
    public static QuestionDTO toQuestionDTO(Question question) {
        return QuestionDTO.builder()
            .id(question.getId())
            .date(question.getDate())
            .urgent(question.isUrgent())
            .texte(question.getTexte())
            .patient(question.getPatient())
            .build();
    }

    public static Question fromQuestionDTO(QuestionDTO questionDTO) {
        return Question.builder()
            .id(questionDTO.id())
            .date(questionDTO.date())
            .urgent(questionDTO.urgent())
            .texte(questionDTO.texte())
            .patient(questionDTO.patient())
            .build();
    }
}

