package com.example.partidosya.repository;

import com.example.partidosya.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    // Método para verificar si ya existe un usuario con el mismo username
    boolean existsByUsername(String username);

    // Método para verificar si ya existe un usuario con el mismo email
    boolean existsByEmail(String email);
}