package com.app.backend.business.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.backend.business.services.UserService;
import com.app.backend.dao.entities.User;
import com.app.backend.dao.enums.Role;
import com.app.backend.dao.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(String id) {

        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        
        return this.userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Horaire with id: " + id + " not found"));
   
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findDoctors() {
        return userRepository.findByRole(Role.DOCTOR);
    }
}
