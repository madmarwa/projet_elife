package com.app.backend.web.dto;

import java.util.Date;
import java.sql.Time;
import com.app.backend.dao.entities.RendezVous;
import com.app.backend.dao.entities.User;

import lombok.Builder;

@Builder
public record RendezVousDTO(
    String id,
    Date dateDemande,
    Date dateRDV,
    Time heureRDV,
    boolean valide,
    boolean vu,
    User patient,
    User medecin
) {
    public static RendezVousDTO toRendezVousDTO(RendezVous rendezVous) {
        return RendezVousDTO.builder()
            .id(rendezVous.getId())
            .dateDemande(rendezVous.getDateDemande())
            .dateRDV(rendezVous.getDateRDV())
            .heureRDV(rendezVous.getHeureRDV())
            .valide(rendezVous.isValide())
            .vu(rendezVous.isVu())
            .patient(rendezVous.getPatient())
            .medecin(rendezVous.getMedecin())
            .build();
    }

    public static RendezVous fromRendezVousDTO(RendezVousDTO rendezVousDTO) {
        return RendezVous.builder()
            .id(rendezVousDTO.id())
            .dateDemande(rendezVousDTO.dateDemande())
            .dateRDV(rendezVousDTO.dateRDV())
            .heureRDV(rendezVousDTO.heureRDV())
            .valide(rendezVousDTO.valide())
            .vu(rendezVousDTO.vu())
            .patient(rendezVousDTO.patient())
            .medecin(rendezVousDTO.medecin())
            .build();
    }
}

