package com.app.backend.business.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.backend.business.services.RoleService;
import com.app.backend.dao.entities.ERole;
import com.app.backend.dao.entities.Role;
import com.app.backend.dao.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<Role> getAllRoles(){ 
        return roleRepository.findAll();

    }


    @Override
    public Optional<Role> getRoleById(Long id){
        return roleRepository.findById(id);
    }


    @Override
    public Optional<Role> getRoleByName(ERole name){
        return roleRepository.findByName(name);
    }
}
