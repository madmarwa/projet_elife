package com.app.backend.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.backend.business.services.SpecialiteService;
import com.app.backend.dao.entities.Speciality;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/specialites")
public class SpecialiteController {

    @Autowired
    private SpecialiteService specialiteService;

    @GetMapping
    public ResponseEntity<List<Speciality>> getAllSpecialites() {
        List<Speciality> specialites = specialiteService.findAll();
        return new ResponseEntity<>(specialites, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Speciality> getSpecialiteById(@PathVariable String id) {
        Optional<Speciality> specialite = specialiteService.findById(id);
        return specialite.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                         .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Speciality> createSpecialite(@RequestBody Speciality specialite) {
        Speciality savedSpecialite = specialiteService.save(specialite);
        return new ResponseEntity<>(savedSpecialite, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Speciality> updateSpecialite(@PathVariable String id, @RequestBody Speciality specialite) {
        Optional<Speciality> existingSpecialite = specialiteService.findById(id);
        if (existingSpecialite.isPresent()) {
            specialite.setId(id);
            Speciality updatedSpecialite = specialiteService.save(specialite);
            return new ResponseEntity<>(updatedSpecialite, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialite(@PathVariable String id) {
        specialiteService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
