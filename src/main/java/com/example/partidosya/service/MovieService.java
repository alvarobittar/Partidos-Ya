package com.example.partidosya.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class MovieService {

    @Value("${api_base_url}")  //URL de la API
    private String apiBaseUrl;

    @Value("${jwt.secret}") //key para acceder a la API
    private String apiKey;

    public String getTrendingMovies() {
        String url = apiBaseUrl + "/trending/movie/day?language=en-US&api_key=" + apiKey;
        return fetchMovies(url);
    }

    public String getPopularMovies() {
        String url = apiBaseUrl + "/movie/popular?language=en-US&api_key=" + apiKey;
        return fetchMovies(url);
    }

    public String getTopRatedMovies() {
        String url = apiBaseUrl + "/movie/top_rated?language=en-US&api_key=" + apiKey;
        return fetchMovies(url);
    }

    public String getScreenMovieId(String id) {
        String url = apiBaseUrl + "/movie/" + id + "?language=es-ES&api_key=" + apiKey;
        return fetchMovies(url);
    }

    public String getSearchMovies(String query) {
        String url = apiBaseUrl + "/search/movie?language=es-ES&query=" + query + "&api_key=" + apiKey;
        return fetchMovies(url);
    }


    public boolean movieExists(int movieId) {
        String url = apiBaseUrl + "/movie/" + movieId + "?language=es-ES&api_key=" + apiKey;
        String response = fetchMovies(url);
        return !response.contains("status_code");
    }


    private String fetchMovies(String url) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("Authorization", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Error al obtener películas", e);
        }
    }
}