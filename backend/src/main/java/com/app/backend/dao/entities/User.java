package com.app.backend.dao.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private Long id;

    private String name;
    private String lastName;
    private String sexe;
    private Date birthDate;
    private Date dateInscri;
    private String email;
    private String phone;
    private String profilPhoto;
    private String password;
    private String address;
    
    @DBRef
    private Role role;
}