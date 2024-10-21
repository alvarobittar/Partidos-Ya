package com.example.partidosya.controller;

import com.example.partidosya.models.MovieList;
import com.example.partidosya.service.MovieListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movielist")
public class MovieListController {
    private final MovieListService movieListService;

    public MovieListController(MovieListService movieListService) {
        this.movieListService = movieListService;
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestParam Long userId, @RequestParam int movieId, @RequestParam MovieList.MovieStatus status) {
        try {
            movieListService.addMovie(userId, movieId, status);
            return ResponseEntity.ok("Movie added/updated successfully."); // Película añadida/actualizada correctamente
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}/watchlist")
    public ResponseEntity<List<MovieList>> getWatchlist(@PathVariable Long userId) {
        return ResponseEntity.ok(movieListService.getUserMovies(userId, MovieList.MovieStatus.WATCHLIST));
    }

    @GetMapping("/user/{userId}/seen")
    public ResponseEntity<List<MovieList>> getSeenMovies(@PathVariable Long userId) {
        return ResponseEntity.ok(movieListService.getUserMovies(userId, MovieList.MovieStatus.SEEN));
    }

    @DeleteMapping("/user/{userId}/remove")
    public ResponseEntity<String> removeMovie(@RequestParam Long userId, @RequestParam int movieId) {
        try {
            movieListService.removeMovie(userId, movieId);
            return ResponseEntity.ok("Movie Deleted."); // Película eliminada
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
