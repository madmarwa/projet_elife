package com.app.backend.business.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.backend.business.services.SpecialiteService;
import com.app.backend.dao.entities.Specialite;
import com.app.backend.dao.repositories.SpecialiteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialiteServiceImpl implements SpecialiteService {

    @Autowired
    private SpecialiteRepository specialiteRepository;

    @Override
    public List<Specialite> findAll() {
        return specialiteRepository.findAll();
    }

    @Override
    public Optional<Specialite> findById(Long id) {
        return specialiteRepository.findById(id);
    }

    @Override
    public Specialite save(Specialite specialite) {
        return specialiteRepository.save(specialite);
    }

    @Override
    public void deleteById(Long id) {
        specialiteRepository.deleteById(id);
    }
}
