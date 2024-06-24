package com.app.backend.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.app.backend.dao.entities.Doctor;

@RepositoryRestResource
public interface DoctorRepository extends MongoRepository<Doctor, Long> {

}
