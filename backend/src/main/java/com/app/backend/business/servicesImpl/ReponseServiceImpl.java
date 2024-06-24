package com.app.backend.business.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.backend.business.services.ReponseService;
import com.app.backend.dao.entities.Reponse;
import com.app.backend.dao.repositories.ReponseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReponseServiceImpl implements ReponseService {

    @Autowired
    private ReponseRepository reponseRepository;

    @Override
    public Reponse createReponse(Reponse reponse) {
        return reponseRepository.save(reponse);
    }

    @Override
    public List<Reponse> getAllReponses() {
        return reponseRepository.findAll();
    }

    @Override
    public Optional<Reponse> getReponseById(Long id) {
        return reponseRepository.findById(id);
    }

    @Override
    public Optional<Reponse> updateReponse(Long id, Reponse reponse) {
        return reponseRepository.findById(id).map(existingReponse -> {
            existingReponse.setDateReponse(reponse.getDateReponse());
            existingReponse.setVisible(reponse.isVisible());
            existingReponse.setTexte(reponse.getTexte());
            existingReponse.setMedecin(reponse.getMedecin());
            existingReponse.setQuestion(reponse.getQuestion());
            return reponseRepository.save(existingReponse);
        });
    }

    @Override
    public boolean deleteReponse(Long id) {
        return reponseRepository.findById(id).map(reponse -> {
            reponseRepository.delete(reponse);
            return true;
        }).orElse(false);
    }

    @Override
    public void deleteAllReponses() {
        reponseRepository.deleteAll();
    }
}

