package com.app.backend.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "speciality")
@Builder
public class Speciality {
    @Id
    private String id;
    private String name;
}