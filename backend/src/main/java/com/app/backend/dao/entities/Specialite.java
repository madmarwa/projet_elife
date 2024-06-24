package com.app.backend.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "specialite")
public class Specialite {
    @Id
    private Long id;
    private String name;
}