package com.app.backend.business.services;


import java.util.List;
import java.util.Optional;

import com.app.backend.dao.entities.Reponse;

public interface ReponseService {
    Reponse createReponse(Reponse reponse);
    List<Reponse> getAllReponses();
    Optional<Reponse> getReponseById(Long id);
    Optional<Reponse> updateReponse(Long id, Reponse reponse);
    boolean deleteReponse(Long id);
    void deleteAllReponses();
}

