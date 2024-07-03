package com.app.backend.business.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.backend.business.services.PhotoService;
import com.app.backend.dao.entities.Photo;
import com.app.backend.dao.repositories.PhotoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    @Override
    public Photo getPhotoById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return photoRepository.findById(id) .orElseThrow(() -> new EntityNotFoundException("Horaire with id: " + id + " not found"));
    }


    @Override
    public Photo createPhoto(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public Optional<Photo> updatePhoto(String id, Photo photo) {
        return photoRepository.findById(id).map(existingPhoto -> {
            existingPhoto.setFile(photo.getFile());
            existingPhoto.setShareDate(photo.getShareDate());
            return photoRepository.save(existingPhoto);
        });
    }

    @Override
    public boolean deletePhoto(String id) {
        return photoRepository.findById(id).map(photo -> {
            photoRepository.delete(photo);
            return true;
        }).orElse(false);
    }

    @Override
    public void deleteAllPhotos() {
        photoRepository.deleteAll();
    }

}
