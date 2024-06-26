package com.app.backend.dao.repositories;


import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.app.backend.dao.entities.User;


@RepositoryRestResource
public interface UserRepository extends MongoRepository<User, Long>  {
    Optional<User>  findByEmail(Long id);

}
