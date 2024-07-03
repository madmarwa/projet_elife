package com.app.backend.web.dto;

import java.time.LocalDate;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.backend.dao.entities.Speciality;
import com.app.backend.dao.entities.User;
import com.app.backend.dao.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterUserDTO(
        @NotBlank(message = "firstname is required") String firstname,
        @NotBlank(message = "lastname is required") String lastname,
        @NotBlank(message = "email is required") @Email(message = "email format is not valid") String email,
        @NotBlank(message = "password is required") @Size(min = 6, message = "Password must be at least 6 characters long") String password,
        String sexe,
        LocalDate birthDate,
        String phone,
        String address,
        boolean active,
        Speciality speciality,
        @NotNull Role role) {
  
    public static User fromRegisterUserDTO(RegisterUserDTO registerUserDTO, PasswordEncoder passwordEncoder) {


        return User.builder()
                .firstname(registerUserDTO.firstname())
                .lastname(registerUserDTO.lastname())
                .email(registerUserDTO.email())
                .password(passwordEncoder.encode(registerUserDTO.password()))
                .sexe(registerUserDTO.sexe())
                .birthDate(registerUserDTO.birthDate())
                .phone(registerUserDTO.phone())
                .address(registerUserDTO.address())
                .active(registerUserDTO.active()) 
                .speciality(registerUserDTO.speciality())
                .role(registerUserDTO.role())
                .build();
    }

    public static RegisterUserDTO toRegisterUserDTO(User user) {
        System.out.println(" ");System.out.println("dto.toUser");
        return new RegisterUserDTO(
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword(),
                user.getSexe(),
                user.getBirthDate(),
                user.getPhone(),
                user.getAddress(),
                user.isActive(),
                user.getSpeciality(),
                user.getRole());
    }
}