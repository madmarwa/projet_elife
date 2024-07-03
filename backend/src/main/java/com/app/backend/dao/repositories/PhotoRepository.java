package com.app.backend.dao.repositories;

import com.app.backend.dao.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.app.backend.dao.entities.Photo;

import java.util.Optional;

@RepositoryRestResource
public interface PhotoRepository extends MongoRepository<Photo, String> {
    Optional<Photo> findByUserAndPhotoProfil(User user, boolean photoProfil) ;


}
