package com.app.backend.business.servicesImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.backend.business.services.RendezVousService;
import com.app.backend.dao.entities.RendezVous;
import com.app.backend.dao.repositories.RendezVousRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RendezVousServiceImpl implements RendezVousService {

    @Autowired
    private RendezVousRepository rendezVousRepository;

    @Override
    public List<RendezVous> getAllRendezVous() {
        return rendezVousRepository.findAll();
    }

    @Override
    public Optional<RendezVous> getRendezVousById(Long id) {
        return rendezVousRepository.findById(id);
    }

    @Override
    public RendezVous createRendezVous(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Optional<RendezVous> updateRendezVous(Long id, RendezVous rendezVous) {
        return rendezVousRepository.findById(id).map(existingRendezVous -> {
            existingRendezVous.setDateDemande(rendezVous.getDateDemande());
            existingRendezVous.setDate(rendezVous.getDate());
            existingRendezVous.setHeure(rendezVous.getHeure());
            existingRendezVous.setValide(rendezVous.isValide());
            existingRendezVous.setVu(rendezVous.isVu());
            existingRendezVous.setPatient(rendezVous.getPatient());
            existingRendezVous.setMedecin(rendezVous.getMedecin());
            return rendezVousRepository.save(existingRendezVous);
        });
    }

    @Override
    public boolean deleteRendezVous(Long id) {
        return rendezVousRepository.findById(id).map(rendezVous -> {
            rendezVousRepository.delete(rendezVous);
            return true;
        }).orElse(false);
    }

    @Override
    public void deleteAllRendezVous() {
        rendezVousRepository.deleteAll();
    }
}

