package com.app.backend.dao.repositories;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.app.backend.dao.entities.Doctor;
import com.app.backend.dao.entities.Horaire;

@RepositoryRestResource
public interface HoraireRepository extends MongoRepository<Horaire, Long> {

    List<Horaire> findAllByMedecin(Doctor medecin);

}
