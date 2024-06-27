package com.app.backend.web.controller;

import com.app.backend.business.services.PhotoService;
import com.app.backend.dao.entities.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);

    @GetMapping
    public ResponseEntity<List<Photo>> getAllPhotos() {
        try {
            List<Photo> photos = photoService.getAllPhotos();
            if (photos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(photos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("An error occurred while fetching all photos", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Photo> getPhotoById(@PathVariable("id") String id) {
        try {
            Optional<Photo> photoData = photoService.getPhotoById(id);
            if (photoData.isPresent()) {
                return new ResponseEntity<>(photoData.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("An error occurred while fetching the photo with id: " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Photo> createPhoto(@RequestBody Photo photo) {
        try {
            Photo savedPhoto = photoService.createPhoto(photo);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedPhoto.getId()).toUri();
            return ResponseEntity.created(location).body(savedPhoto);
        } catch (Exception e) {
            logger.error("An error occurred while creating a new photo", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Photo> updatePhoto(@PathVariable("id") String id, @RequestBody Photo photo) {
        try {
            Optional<Photo> updatedPhoto = photoService.updatePhoto(id, photo);
            if (updatedPhoto.isPresent()) {
                return new ResponseEntity<>(updatedPhoto.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("An error occurred while updating the photo with id: " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePhoto(@PathVariable("id") String id) {
        try {
            boolean deleted = photoService.deletePhoto(id);
            if (deleted) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error("An error occurred while deleting the photo with id: " + id, e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllPhotos() {
        try {
            photoService.deleteAllPhotos();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error("An error occurred while deleting all photos", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}