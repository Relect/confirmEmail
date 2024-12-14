package com.example.confirmEmail.service;

import com.example.confirmEmail.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

public interface UserService {

    ResponseEntity<?> saveUser(User user);
    ResponseEntity<?> confirmEmail(String token);
}
