package com.app.backend.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.backend.web.models.responses.ResponseMessage;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    
    @GetMapping()
    //@PreAuthorize("hasRole('ADMIN') and hasAuthority('READ_PRIVILEGE')")
    public ResponseEntity<?> getAdminBoard() {
        return new ResponseEntity<>(new ResponseMessage("Admin Board"), HttpStatus.OK);
    }
    
    
}
