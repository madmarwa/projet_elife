package com.app.backend.dao.entities;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Time;


import java.util.Date;

@Document(collection = "rdv")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class RendezVous {
    @Id
    private String id;
    //date demande de RDV
    private Date dateDemande;
    //date demandée pour le RDV
    private Date dateRDV;
    private Time heureRDV;
    private boolean valide;
    private boolean vu;
    @DBRef
    private User patient;
    @DBRef
    private User medecin;

}
