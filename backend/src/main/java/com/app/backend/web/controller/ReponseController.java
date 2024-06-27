package com.app.backend.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.backend.business.services.ReponseService;
import com.app.backend.dao.entities.Reponse;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reponses")
public class ReponseController {

    @Autowired
    private ReponseService reponseService;

    @GetMapping
    public ResponseEntity<List<Reponse>> getAllReponses() {
        try {
            List<Reponse> reponses = reponseService.getAllReponses();
            if (reponses.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reponses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reponse> getReponseById(@PathVariable("id") String id) {
        try {
            Optional<Reponse> reponseData = reponseService.getReponseById(id);
            if (reponseData.isPresent()) {
                return new ResponseEntity<>(reponseData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Reponse> createReponse(@RequestBody Reponse reponse) {
        try {
            Reponse savedReponse = reponseService.createReponse(reponse);
            URI location = URI.create("/api/reponses/" + savedReponse.getId());
            return ResponseEntity.created(location).body(savedReponse);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse> updateReponse(@PathVariable("id") String id, @RequestBody Reponse reponse) {
        try {
            Optional<Reponse> updatedReponse = reponseService.updateReponse(id, reponse);
            if (updatedReponse.isPresent()) {
                return ResponseEntity.ok(updatedReponse.get());
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteReponse(@PathVariable("id") String id) {
        try {
            boolean deleted = reponseService.deleteReponse(id);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllReponses() {
        try {
            reponseService.deleteAllReponses();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

