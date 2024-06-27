package com.app.backend.business.services;


import java.util.List;
import java.util.Optional;

import com.app.backend.dao.entities.RendezVous;

public interface RendezVousService {
    RendezVous createRendezVous(RendezVous rendezVous);
    List<RendezVous> getAllRendezVous();
    Optional<RendezVous> getRendezVousById(String id);
    Optional<RendezVous> updateRendezVous(String id, RendezVous rendezVous);
    boolean deleteRendezVous(String id);
    void deleteAllRendezVous();
}

