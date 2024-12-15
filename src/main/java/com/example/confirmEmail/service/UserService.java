package com.example.confirmEmail.service;

import com.example.confirmEmail.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> saveUser(User user);
    ResponseEntity<?> confirmEmail(String token);
}
