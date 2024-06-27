package com.app.backend.business.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.app.backend.business.services.HoraireService;
import com.app.backend.dao.entities.Horaire;
import com.app.backend.dao.entities.User;
import com.app.backend.dao.repositories.HoraireRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class HoraireServiceImpl implements HoraireService {

    @Autowired
    private HoraireRepository horaireRepository;

    @Override
    public List<Horaire> findAll() {
        return horaireRepository.findAll();
    }

    @Override
    public Horaire findById(String id) {

        // Check if the ID is null and throw an IllegalArgumentException if it is
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        // Retrieve the horaire by ID, throw an EntityNotFoundException if not found
        return this.horaireRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("horaire with id: " + id + " not found"));

    }

    @Override
    public List<Horaire> findByMedecin(User medecin) {
        return horaireRepository.findAllByMedecin(medecin);
    }

    @Override
    public Horaire save(Horaire horaire) {
        return horaireRepository.save(horaire);
          }

    @Override
    public void deleteById(String id) {
        horaireRepository.deleteById(id);
          }

          
    public Horaire getHoraireById(String id) {
        
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        
        return this.horaireRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Horaire with id: " + id + " not found"));
    }

    @Override
    public Horaire updateHoraire(String id, Horaire horaire) {
        if (id == null || horaire == null) {
            throw new IllegalArgumentException("ID or Horaire cannot be null");
        }

        // Verify the existence of the horaire
        getHoraireById(id);
            return horaireRepository.save(horaire);
            
    }


    @Override
    public List<Horaire> getAllHoraires() {
        return this.horaireRepository.findAll();
    }

}
