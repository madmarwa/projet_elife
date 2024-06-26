package com.app.backend.dao.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.app.backend.dao.entities.Role;
import com.app.backend.dao.enums.ERole;

@RepositoryRestResource
public interface RoleRepository extends MongoRepository<Role, Long>  {
    Optional<Role> findByName(ERole name);
}
