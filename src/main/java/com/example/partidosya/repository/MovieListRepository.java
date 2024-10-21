package com.example.partidosya.repository;

import com.example.partidosya.models.MovieList;
import com.example.partidosya.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieListRepository extends JpaRepository<MovieList, Long> {
    List<MovieList> findByUser(User user);
    List<MovieList> findByUserAndStatus(User user, MovieList.MovieStatus status);
}


