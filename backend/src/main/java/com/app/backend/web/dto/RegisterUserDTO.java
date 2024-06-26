package com.app.backend.web.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.backend.dao.entities.User;
import com.app.backend.dao.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterUserDTO(
        @NotBlank(message = "firstname is required") String name,
        @NotBlank(message = "lastname is required") String lastname,
        @NotBlank(message = "email is required") @Email(message = "email format is not valid") String email,
        @NotBlank(message = "password is required")  @Size(min = 6, message = "Password must be at most 6 characters long") String password,
        @NotNull Role role) {
  
            public static User fromRegisterUserDTO(RegisterUserDTO registerUserDTO, PasswordEncoder passwordEncoder) {
        User user = User.builder()
                .name(registerUserDTO.name())
                .lastName(registerUserDTO.lastname())
                .email(registerUserDTO.email())
                .password(passwordEncoder.encode(registerUserDTO.password()))
                .role(registerUserDTO.role())
                .build();
        return user;
    }

    public static RegisterUserDTO toRegisterUserDTO(User user) {
        return new RegisterUserDTO(user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole());
    }
}
