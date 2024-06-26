package com.app.backend.business.services;


import org.springframework.security.core.Authentication;

import com.app.backend.dao.entities.User;
import com.app.backend.exceptions.DuplicateUserException;
import com.app.backend.web.dto.AuthenticationUserDTO;

public interface AuthenticationService {
   
    User register(User user) throws DuplicateUserException;
   AuthenticationUserDTO login(Authentication authentication);
}
