package com.app.backend.web.dto;

import java.util.Date;
import com.app.backend.dao.entities.Reponse;
import com.app.backend.dao.entities.User;
import com.app.backend.dao.entities.Question;

import lombok.Builder;

@Builder
public record ReponseDTO(
    String id,
    Date dateReponse,
    boolean visible,
    String texte,
    User medecin,
    Question question
) {
    public static ReponseDTO toReponseDTO(Reponse reponse) {
        return ReponseDTO.builder()
            .id(reponse.getId())
            .dateReponse(reponse.getDateReponse())
            .visible(reponse.isVisible())
            .texte(reponse.getTexte())
            .medecin(reponse.getMedecin())
            .question(reponse.getQuestion())
            .build();
    }

    public static Reponse fromReponseDTO(ReponseDTO reponseDTO) {
        return Reponse.builder()
            .id(reponseDTO.id())
            .dateReponse(reponseDTO.dateReponse())
            .visible(reponseDTO.visible())
            .texte(reponseDTO.texte())
            .medecin(reponseDTO.medecin())
            .question(reponseDTO.question())
            .build();
    }
}

