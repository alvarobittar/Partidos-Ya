package com.example.Storyloom.service;

import com.example.Storyloom.dto.MovieDetails;
import com.example.Storyloom.models.MovieList;
import com.example.Storyloom.models.User;
import com.example.Storyloom.repository.MovieListRepository;
import com.example.Storyloom.repository.UserRepository;
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
                .orElseThrow(() -> new RuntimeException("User not found"));

        //Details
        MovieDetails movieDetails = movieService.getMovieDetails(movieId);
        if (movieDetails == null) {
            throw new RuntimeException("the movie has not been found");
        }

        //Verification existing movie
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
                .orElseThrow(() -> new RuntimeException("User not found"));
        return movieListRepository.findByUserAndStatus(user, status);
    }

    public void removeMovie(Long userId, int movieId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));


        movieListRepository.findByUserAndStatus(user, MovieList.MovieStatus.WATCHLIST)
                .stream()
                .filter(m -> m.getMovieId() == movieId)
                .forEach(movieListRepository::delete);


        movieListRepository.findByUserAndStatus(user, MovieList.MovieStatus.SEEN)
                .stream()
                .filter(m -> m.getMovieId() == movieId)
                .forEach(movieListRepository::delete);
    }

}
