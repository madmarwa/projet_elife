package com.app.backend.business.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.backend.business.services.SpecialiteService;
import com.app.backend.dao.entities.Speciality;
import com.app.backend.dao.repositories.SpecialiteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialiteServiceImpl implements SpecialiteService {

    @Autowired
    private SpecialiteRepository specialiteRepository;

    @Override
    public List<Speciality> findAll() {
        return specialiteRepository.findAll();
    }

    @Override
    public Optional<Speciality> findById(String id) {
        return specialiteRepository.findById(id);
    }

    @Override
    public Speciality save(Speciality specialite) {
        return specialiteRepository.save(specialite);
    }

    @Override
    public void deleteById(String id) {
        specialiteRepository.deleteById(id);
    }
}
