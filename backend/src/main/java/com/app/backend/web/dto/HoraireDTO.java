package com.app.backend.web.dto;

import com.app.backend.dao.entities.Horaire;
import com.app.backend.dao.entities.User;

import java.sql.Time;
import lombok.Builder;

@Builder
public record HoraireDTO(
    String id,
    String jour,
    Time debutMatin,
    Time finMatin,
    Time debutApMidi,
    Time finApMidi,
    User medecin
) {
    public static HoraireDTO toHoraireDTO(Horaire horaire) {
        return HoraireDTO.builder()
            .id(horaire.getId())
            .jour(horaire.getJour())
            .debutMatin(horaire.getDebutMatin())
            .finMatin(horaire.getFinMatin())
            .debutApMidi(horaire.getDebutApMidi())
            .finApMidi(horaire.getFinApMidi())
            .medecin(horaire.getMedecin())
            .build();
    }

    public static Horaire fromHoraireDTO(HoraireDTO horaireDTO) {
        return Horaire.builder()
            .id(horaireDTO.id)
            .jour(horaireDTO.jour)
            .debutMatin(horaireDTO.debutMatin)
            .finMatin(horaireDTO.finMatin)
            .debutApMidi(horaireDTO.debutApMidi)
            .finApMidi(horaireDTO.finApMidi)
            .medecin(horaireDTO.medecin)
            .build();
    }
}
