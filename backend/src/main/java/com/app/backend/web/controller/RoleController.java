package com.app.backend.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.backend.business.services.RoleService;
import com.app.backend.dao.entities.Role;
import com.app.backend.dao.enums.ERole;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        try {
            List<Role> roles = roleService.getAllRoles();
            if (roles.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(roles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable("id") Long id) {
        try {
            Optional<Role> roleData = roleService.getRoleById(id);
            if (roleData.isPresent()) {
                return new ResponseEntity<>(roleData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/name/{role}")
    public ResponseEntity<Role> getRoleById(@PathVariable("role") String role){
        Optional<Role> roleData=null;
        try {
            switch (role) {
                case "admin":
                    roleData = roleService.getRoleByName(ERole.ROLE_ADMIN);
                break;
                
                case "doctor":
                    roleData = roleService.getRoleByName(ERole.ROLE_DOCTOR);
                break;

                case "patient":
                    roleData = roleService.getRoleByName(ERole.ROLE_PATIENT);
                break;

                default:
                    roleData = roleService.getRoleByName(ERole.ROLE_USER);
            }

            if (roleData.isPresent()) {
                return new ResponseEntity<>(roleData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
