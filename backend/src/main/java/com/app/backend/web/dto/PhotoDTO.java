package com.app.backend.web.dto;


import java.util.Date;

import com.app.backend.dao.entities.Photo;
import com.app.backend.dao.entities.User;

import lombok.Builder;

@Builder
public record PhotoDTO(
    String id,
    String name,
    byte[] file,
    boolean photoProfil,
    Date shareDate,
    User user
) {
    public static PhotoDTO toPhotoDTO(Photo photo) {
        return PhotoDTO.builder()
            .id(photo.getId())
            .name(photo.getName())
            .file(photo.getFile())
            .photoProfil(photo.isPhotoProfil())
            .shareDate(photo.getShareDate())
            .user(photo.getUser())
            .build();
    }

    public static Photo fromPhotoDTO(PhotoDTO photoDTO) {
        return Photo.builder()
            .id(photoDTO.id())
            .name(photoDTO.name())
            .file(photoDTO.file())
            .photoProfil(photoDTO.photoProfil())
            .shareDate(photoDTO.shareDate())
            .user(photoDTO.user())
            .build();
    }
}

