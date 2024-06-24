package com.app.backend.business.services;

import java.util.List;
import java.util.Optional;

import com.app.backend.dao.entities.Photo;

public interface PhotoService {

    Photo createPhoto(Photo photo);
    List<Photo> getAllPhotos();
    Optional<Photo> getPhotoById(Long id);
    Optional<Photo> updatePhoto(Long id, Photo photo);
    boolean deletePhoto(Long id);
    void deleteAllPhotos();
}
