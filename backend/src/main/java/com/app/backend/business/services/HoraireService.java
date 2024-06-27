package com.app.backend.business.services;

import java.util.List;


import com.app.backend.dao.entities.Horaire;
import com.app.backend.dao.entities.User;

public interface HoraireService {
    List<Horaire> findAll();
    Horaire findById(String id);
    List<Horaire> findByMedecin(User medecin);
    Horaire save(Horaire horaire);
    void deleteById(String id);

    Horaire updateHoraire(String id,Horaire horaire);
    
    List<Horaire> getAllHoraires();
}
