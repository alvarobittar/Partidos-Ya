package com.example.Storyloom.controller;

import com.example.Storyloom.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {

        this.movieService = movieService;
    }

    @GetMapping("/trending")
    public ResponseEntity<String> getTrendingMovies() {
        String trendingMovies = movieService.getTrendingMovies();
        return ResponseEntity.ok(trendingMovies);
    }

    @GetMapping("/popular")
    public ResponseEntity<String> getPopularMovies() {
        String popularMovies = movieService.getPopularMovies();
        return ResponseEntity.ok(popularMovies);
    }

    @GetMapping("/top_rated")
    public ResponseEntity<String> getTopRatedMovies() {
        String topRatedMovies = movieService.getTopRatedMovies();
        return ResponseEntity.ok(topRatedMovies);
    }

    @GetMapping("/screen/{id}")
    public ResponseEntity<String> getScreenMovieId(@PathVariable String id) {
        String screenMovieId = movieService.getScreenMovieId(id);
        return ResponseEntity.ok(screenMovieId);
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<String> getSearchMovies(@PathVariable String query) {
        String searchMovies = movieService.getSearchMovies(query);
        return ResponseEntity.ok(searchMovies);
    }


}






