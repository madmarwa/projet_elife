package com.app.backend.dao.repositories;


import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.app.backend.dao.entities.User;
import com.app.backend.dao.enums.Role;


@RepositoryRestResource
public interface UserRepository extends MongoRepository<User,String>  {
    Optional<User>  findByEmail(String email);
    List<User>  findByRole(Role role);

}
