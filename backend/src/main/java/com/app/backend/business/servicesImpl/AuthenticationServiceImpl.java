package com.app.backend.business.servicesImpl;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.app.backend.business.services.AuthenticationService;
import com.app.backend.dao.entities.User;
import com.app.backend.dao.repositories.UserRepository;
import com.app.backend.exceptions.DuplicateUserException;
import com.app.backend.web.dto.AuthenticationUserDTO;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    // Repository to handle User entity persistence
    private final UserRepository userRepository;

    // Constructor injection for UserRepository
    public AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) throws DuplicateUserException {
        if (user == null) {
            return null;
        }
        try {
            // Save the user in the repository
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            // Handle uniqueness constraint violations
            throw new DuplicateUserException("User already exists");
        }
    }

    @Override
    public AuthenticationUserDTO login(Authentication authentication) {
        System.out.println(" ");System.out.println("S.auth");
        // Retrieve the user principal from the authentication object after basic authentication
        User user = (User) authentication.getPrincipal();
        // Convert the User entity to AuthenticationUserDTO and return it
        return AuthenticationUserDTO.toAuthenticationUserDTO(user);
    }
}