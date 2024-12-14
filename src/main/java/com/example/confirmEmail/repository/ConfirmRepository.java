package com.example.confirmEmail.repository;

import com.example.confirmEmail.model.ConfirmToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmRepository extends JpaRepository<ConfirmToken, Long> {

    ConfirmToken findByToken(String token);
}
