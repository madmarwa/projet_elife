package com.app.backend.business.services;

import java.util.List;

import com.app.backend.dao.entities.User;

public interface UserService {
    List<User> findAll();
    User findById(String id);
    User save(User user);
    void deleteById(String id);
    List<User> findDoctors();
}
