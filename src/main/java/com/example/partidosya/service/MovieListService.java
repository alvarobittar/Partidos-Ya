package com.example.partidosya.service;

import com.example.partidosya.models.MovieList;
import com.example.partidosya.models.User;
import com.example.partidosya.repository.MovieListRepository;
import com.example.partidosya.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieListService {
    private final MovieListRepository movieListRepository;
    private final UserRepository userRepository;
    private final MovieService movieService;

    public MovieListService(MovieListRepository movieListRepository, UserRepository userRepository, MovieService movieService) {
        this.movieListRepository = movieListRepository;
        this.userRepository = userRepository;
        this.movieService = movieService;
    }

    public void addMovie(Long userId, int movieId, MovieList.MovieStatus status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Verificar si la película existe en la API de TMDB
        if (!movieService.movieExists(movieId)) {
            throw new RuntimeException("The movie with the ID provided does not exist.");
        }

        // Verificar si la película ya existe en la lista del usuario
        Optional<MovieList> existingEntry = movieListRepository.findByUser(user).stream()
                .filter(m -> m.getMovieId() == movieId)
                .findFirst();

        if (existingEntry.isPresent()) {
            // Actualizar el estado si la película ya está en la lista
            MovieList movieList = existingEntry.get();
            movieList.setStatus(status);
            movieListRepository.save(movieList);
        } else {
            // Crear una nueva entrada si la película no está en la lista
            MovieList newMovie = MovieList.builder()
                    .user(user)
                    .movieId(movieId)
                    .status(status)
                    .build();
            movieListRepository.save(newMovie);
        }
    }

    public List<MovieList> getUserMovies(Long userId, MovieList.MovieStatus status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return movieListRepository.findByUserAndStatus(user, status);
    }

    public void removeMovie(Long userId, int movieId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        movieListRepository.findByUser(user).stream()
                .filter(m -> m.getMovieId() == movieId)
                .findFirst()
                .ifPresent(movieListRepository::delete);
    }
}
