package com.app.backend.business.services;

import java.util.List;
import java.util.Optional;

import com.app.backend.dao.entities.Specialite;

public interface SpecialiteService {
    List<Specialite> findAll();
    Optional<Specialite> findById(Long id);
    Specialite save(Specialite specialite);
    void deleteById(Long id);
}
