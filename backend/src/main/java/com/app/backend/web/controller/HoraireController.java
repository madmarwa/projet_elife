package com.app.backend.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;


import com.app.backend.dao.entities.Doctor;
import com.app.backend.dao.entities.Horaire;
import com.app.backend.dao.repositories.DoctorRepository;
import com.app.backend.dao.repositories.HoraireRepository;

@RestController
@RequestMapping("/api/horaire")
public class HoraireController {
    @Autowired
    private HoraireRepository horaireRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping()
    public List<Horaire> getAllHoraire() {
        return horaireRepository.findAll();

    }
    @GetMapping("/{id}")
    public ResponseEntity<Horaire> getHoraireById(@PathVariable("id") Long id) {
        Optional<Horaire> horaireData = horaireRepository.findById(id);

        if (horaireData.isPresent()) {
            return new ResponseEntity<>(horaireData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/doctor/{id}")
    public List<Horaire> getAllHoraireByDoctor(@PathVariable("id") Long id) {
        Doctor medecin=doctorRepository.findById(id).get();
        return horaireRepository.findAllByMedecin(medecin);

    }

    @PostMapping("")
    public ResponseEntity<Horaire> createHoraire(@RequestBody Horaire horaire) {
        Horaire savedHoraire = horaireRepository.save(horaire);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedHoraire.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Horaire> updateHoraire(@PathVariable("id") Long id, @RequestBody Horaire horaire) {

        Optional<Horaire> horaireOptional = horaireRepository.findById(id);
        if (!horaireOptional.isPresent())
            return ResponseEntity.notFound().build();
        horaire.setId(id);
        horaireRepository.save(horaire);
        return ResponseEntity.noContent().build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteHoraire(@PathVariable("id") Long id) {
        try {
            horaireRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllHoraire() {
        try {
            horaireRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
