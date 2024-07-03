package com.app.backend.business.services;

import java.util.List;
import java.util.stream.Stream;

import com.app.backend.dao.entities.Photo;
import com.app.backend.dao.entities.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    List<User> findAll();
    User findById(String id);
    User save(User user);
    void deleteById(String id);
    List<User> findDoctors();

}
