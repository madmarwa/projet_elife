package com.app.backend.business.services;


import java.util.List;
import java.util.Optional;

import com.app.backend.dao.entities.Reponse;

public interface ReponseService {
    Reponse createReponse(Reponse reponse);
    List<Reponse> getAllReponses();
    Optional<Reponse> getReponseById(String id);
    Optional<Reponse> updateReponse(String id, Reponse reponse);
    boolean deleteReponse(String id);
    void deleteAllReponses();
}

