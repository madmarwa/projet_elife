package com.app.backend.dao.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.app.backend.dao.entities.Specialite;

@RepositoryRestResource
public interface SpecialiteRepository  extends MongoRepository<Specialite, Long> {

}
