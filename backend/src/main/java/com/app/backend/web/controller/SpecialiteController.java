package com.app.backend.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.backend.business.services.SpecialiteService;
import com.app.backend.dao.entities.Specialite;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/specialites")
public class SpecialiteController {

    @Autowired
    private SpecialiteService specialiteService;

    @GetMapping
    public ResponseEntity<List<Specialite>> getAllSpecialites() {
        List<Specialite> specialites = specialiteService.findAll();
        return new ResponseEntity<>(specialites, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Specialite> getSpecialiteById(@PathVariable Long id) {
        Optional<Specialite> specialite = specialiteService.findById(id);
        return specialite.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                         .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Specialite> createSpecialite(@RequestBody Specialite specialite) {
        Specialite savedSpecialite = specialiteService.save(specialite);
        return new ResponseEntity<>(savedSpecialite, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Specialite> updateSpecialite(@PathVariable Long id, @RequestBody Specialite specialite) {
        Optional<Specialite> existingSpecialite = specialiteService.findById(id);
        if (existingSpecialite.isPresent()) {
            specialite.setId(id);
            Specialite updatedSpecialite = specialiteService.save(specialite);
            return new ResponseEntity<>(updatedSpecialite, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialite(@PathVariable Long id) {
        specialiteService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
