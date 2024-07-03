package com.app.backend.web.controller;

import com.app.backend.business.services.PhotoService;
import com.app.backend.business.services.UserService;
import com.app.backend.dao.entities.Photo;
import com.app.backend.dao.entities.User;
import com.app.backend.dao.repositories.PhotoRepository;
import com.app.backend.web.dto.PhotoDTO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.io.ByteArrayOutputStream;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private UserService userService;
    
    //@Autowired
    @GetMapping("")
    public ResponseEntity<?> getAllPhoto() {
        
        List<PhotoDTO> photos = photoService.getAllPhotos()
                .stream()
                .map(PhotoDTO::toPhotoDTO)
                
                .collect(Collectors.toList());     
        return new ResponseEntity<>(photos, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPhotoById(@PathVariable("id") String id) {

        PhotoDTO photo = PhotoDTO.toPhotoDTO(photoService.getPhotoById(id));
        return new ResponseEntity<>(photo, HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<?> createPhoto(@RequestBody PhotoDTO photoDTO) {
        Photo photo = PhotoDTO.fromPhotoDTO(photoDTO);
        Photo savedPhoto = this.photoService.createPhoto(photo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPhoto.getId()).toUri();
        return ResponseEntity.created(location).build();


    }

    @PutMapping("/{id}")
    public ResponseEntity<Photo> updatePhoto(@PathVariable("id") String id, @RequestBody PhotoDTO photoDTO) {
        Photo photo = PhotoDTO.fromPhotoDTO(photoDTO);
        Photo photoOptional = photoService.getPhotoById(id);
        if (photoOptional==null)
            return ResponseEntity.notFound().build();
        photo.setId(id);
        photoService.createPhoto(photo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePhoto(@PathVariable("id") String id) {
        try {
            photoService.deletePhoto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/photo/deleteall")
    public ResponseEntity<HttpStatus> deleteAllPhoto() {
        try {
            photoService.deleteAllPhotos();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/photo/files")
    public ResponseEntity<?> handleFileUpload(@RequestBody MultipartFile file) {
        String message = "";
        Photo photo = new Photo();
        try {
            photo.setFile(compressBytes(file.getBytes()));
            photo.setType(file.getContentType());
            photo.setPhotoProfil(false);
            photo.setShareDate(new Date());
            photo.setName(file.getOriginalFilename());
            photoService.createPhoto(photo);

            message = "You successfully uploaded ";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload ";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }



    @PostMapping("/user/profil/{id}")
    public ResponseEntity uploadPhotoProfil(@PathVariable("id") String id,@RequestBody MultipartFile file) {
        String message = "";
        Photo photo = new Photo();
        try {
            photo.setType(file.getContentType());
            photo.setFile(compressBytes(file.getBytes()));
            photo.setPhotoProfil(true);
            photo.setShareDate(new Date());
            photo.setName(file.getOriginalFilename());
            photo.setUser(userService.findById(id));
            photoService.createPhoto(photo);
            message = "You successfully uploaded ";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload ";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getPhotoProfilUser(@PathVariable("id") String id) throws IOException  {
        User user = userService.findById(id);
        Optional<Photo> photoDt = photoRepository.findByUserAndPhotoProfil(user,true);
        if(photoDt.isPresent()){
            Photo photoData=photoDt.get();

            Photo photo= new Photo(photoData.getId(),decompressBytes(photoData.getFile()),photoData.getName(),photoData.getType(),true,photoData.getShareDate(),photoData.getUser());


            PhotoDTO photoDTO = PhotoDTO.toPhotoDTO(photo);
            return new ResponseEntity<>(photoDTO, HttpStatus.OK);
        }else
            return null;

    }


    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}