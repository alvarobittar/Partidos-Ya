package com.example.partidosya.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService {

    private static final String TRENDING_API_URL = "https://api.themoviedb.org/3/trending/movie/day?language=en-US";
    private static final String POPULAR_API_URL = "https://api.themoviedb.org/3/movie/popular?language=en-US";
    private static final String SCREENID_API_URL = "https://api.themoviedb.org/3/movie/{movie_id}?language=es-ES";
    private static final String SEARCH_API_URL = "https://api.themoviedb.org/3/search/movie?language=es-ES&query={query}";

    @Value("${jwt.secret}") //key para acceder a la API encriptada
    private String authHeader;

    public String getTrendingMovies() {
        return fetchMovies(TRENDING_API_URL);
    }

    public String getPopularMovies() {
        return fetchMovies(POPULAR_API_URL);
    }

    public String getScreenMovieId(String id) {
        String url = SCREENID_API_URL.replace("{movie_id}", id);
        return fetchMovies(url);
    }

    public String getSearchMovies(String query) {
        String url = SEARCH_API_URL.replace("{query}", query);
        return fetchMovies(url);
    }


    //public String getUserMovieList(String userId) {
       // return "{\"results\": [{\"id\": 1, \"title\": \"Película 1\", \"poster_path\": \"/path1.jpg\", \"seen\": false}, {\"id\": 2, \"title\": \"Película 2\", \"poster_path\": \"/path2.jpg\", \"seen\": true}]}";
    //}



    private String fetchMovies(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("Authorization", authHeader);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al obtener películas", e);
        }
    }
}
