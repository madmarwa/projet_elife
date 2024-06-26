package com.app.backend.dao.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.app.backend.dao.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;

import java.util.Date;
import java.util.Collection;

import jakarta.persistence.*;

@Builder
@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User  implements UserDetails {

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
    
    
    @Enumerated(EnumType.STRING)
        @Column(nullable = false)

    private Role role;


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
     }
     
      @Override
      public String getPassword() {
        return password;
     }
     
      @Override
      public String getUsername() {
        return email;
     }
     
      @Override
      public boolean isAccountNonExpired() {
        return true;
     }
     
      @Override
      public boolean isAccountNonLocked() {
        return true;
     }
     
      @Override
      public boolean isCredentialsNonExpired() {
        return true;
     }
     
      @Override
      public boolean isEnabled() {
        return true;
     }
}