package com.app.backend.dao.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.internal.connection.Time;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Date;

@Document(collection = "rdv")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //date demande de RDV
    private Date dateDemande;
    //date demandée pour le RDV
    private Date date;
    private Time heure;
    private boolean valide;
    private boolean vu;
    @DBRef
    private User patient;
    @DBRef
    private User medecin;

}
