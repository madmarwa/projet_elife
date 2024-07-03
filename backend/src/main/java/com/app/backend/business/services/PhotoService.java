package com.app.backend.business.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.app.backend.dao.entities.Photo;
import com.app.backend.dao.entities.User;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {

    Photo createPhoto(Photo photo);
    List<Photo> getAllPhotos();
    Photo getPhotoById(String id);
    Optional<Photo> updatePhoto(String id, Photo photo);
    boolean deletePhoto(String id);
    void deleteAllPhotos();

}
