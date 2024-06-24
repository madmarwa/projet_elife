package com.app.backend.dao.entities;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "users")
@TypeAlias("doctor")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doctor {
    private String profil;
    private String experience;
    private boolean enabled;
    private double visitPrice;


}
