package com.app.backend.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.backend.business.services.RendezVousService;
import com.app.backend.dao.entities.RendezVous;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rendezvous")
public class RendezVousController {

    @Autowired
    private RendezVousService rendezVousService;

    private static final Logger logger = LoggerFactory.getLogger(RendezVousController.class);

    @GetMapping
    public ResponseEntity<List<RendezVous>> getAllRendezVous() {
        try {
            List<RendezVous> rendezVousList = rendezVousService.getAllRendezVous();
            if (rendezVousList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(rendezVousList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred while fetching all rendezvous", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RendezVous> getRendezVousById(@PathVariable("id") String id) {
        try {
            Optional<RendezVous> rendezVousData = rendezVousService.getRendezVousById(id);
            if (rendezVousData.isPresent()) {
                return new ResponseEntity<>(rendezVousData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("An error occurred while fetching the rendezvous with id: " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<RendezVous> createRendezVous(@RequestBody RendezVous rendezVous) {
        try {
            RendezVous savedRendezVous = rendezVousService.createRendezVous(rendezVous);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedRendezVous.getId()).toUri();
            return ResponseEntity.created(location).body(savedRendezVous);
        } catch (Exception e) {
            logger.error("An error occurred while creating a new rendezvous", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<RendezVous> updateRendezVous(@PathVariable("id") String id, @RequestBody RendezVous rendezVous) {
        try {
            Optional<RendezVous> updatedRendezVous = rendezVousService.updateRendezVous(id, rendezVous);
            if (updatedRendezVous.isPresent()) {
                return new ResponseEntity<>(updatedRendezVous.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("An error occurred while updating the rendezvous with id: " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRendezVous(@PathVariable("id") String id) {
        try {
            boolean deleted = rendezVousService.deleteRendezVous(id);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("An error occurred while deleting the rendezvous with id: " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllRendezVous() {
        try {
            rendezVousService.deleteAllRendezVous();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("An error occurred while deleting all rendezvous", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

