package com.app.backend.dao.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.mongodb.internal.connection.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Horaire {
    @Id
    private Long id;
    private String jour;
    private Time debutMatin;
    private Time finMatin;
    private Time debutApMidi;
    private Time finApMidi;
    @DBRef
    private Doctor medecin;

}
