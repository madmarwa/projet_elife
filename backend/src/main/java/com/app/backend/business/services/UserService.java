package com.app.backend.business.services;

import java.util.List;
import java.util.Optional;

import com.app.backend.dao.entities.User;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    List<User> findByRole(String role);
    User save(User user);
    void deleteById(Long id);
}
