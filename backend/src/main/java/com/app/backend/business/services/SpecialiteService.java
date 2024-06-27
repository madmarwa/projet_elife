package com.app.backend.business.services;

import java.util.List;
import java.util.Optional;

import com.app.backend.dao.entities.Speciality;

public interface SpecialiteService {
    List<Speciality> findAll();
    Optional<Speciality> findById(String id);
    Speciality save(Speciality specialite);
    void deleteById(String id);
}
