package com.example.Storyloom.repository;

import com.example.Storyloom.models.MovieList;
import com.example.Storyloom.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieListRepository extends JpaRepository<MovieList, Long> {
    List<MovieList> findByUser(User user);
    List<MovieList> findByUserAndStatus(User user, MovieList.MovieStatus status);
}


