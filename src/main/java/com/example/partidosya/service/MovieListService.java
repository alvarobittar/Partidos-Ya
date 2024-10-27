package com.example.partidosya.service;

import com.example.partidosya.dto.MovieDetails;
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

        // Obtener detalles de la película desde la API
        MovieDetails movieDetails = movieService.getMovieDetails(movieId);
        if (movieDetails == null) {
            throw new RuntimeException("La película con el ID proporcionado no existe.");
        }

        // Verificar si la película ya existe en la lista del usuario
        Optional<MovieList> existingEntry = movieListRepository.findByUser(user).stream()
                .filter(m -> m.getMovieId() == movieId)
                .findFirst();

        if (existingEntry.isPresent()) {
            MovieList movieList = existingEntry.get();
            movieList.setStatus(status);
            movieListRepository.save(movieList);
        } else {
            MovieList newMovie = MovieList.builder()
                    .user(user)
                    .movieId(movieId)
                    .title(movieDetails.getTitle())
                    .posterPath(movieDetails.getPosterPath())
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

        // Eliminar de la lista de watchlist
        movieListRepository.findByUserAndStatus(user, MovieList.MovieStatus.WATCHLIST)
                .stream()
                .filter(m -> m.getMovieId() == movieId)
                .forEach(movieListRepository::delete);

        // Eliminar de la lista de seen
        movieListRepository.findByUserAndStatus(user, MovieList.MovieStatus.SEEN)
                .stream()
                .filter(m -> m.getMovieId() == movieId)
                .forEach(movieListRepository::delete);
    }

}
