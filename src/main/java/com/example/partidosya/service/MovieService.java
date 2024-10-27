package com.example.partidosya.service;

import com.example.partidosya.dto.MovieDetails;
import org.json.JSONArray;
import org.json.JSONObject;
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

    //URL de la API
    @Value("${api_base_url}")
    private String apiBaseUrl;

    //key para acceder a la API
    @Value("${jwt.secret}")
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
        // Llamar a la API para obtener detalles de la película
        String url = apiBaseUrl + "/movie/" + id + "?language=en-US&api_key=" + apiKey;
        String jsonResponse = fetchMovies(url);

        // Convertir la respuesta JSON a un objeto para poder manipularlo
        JSONObject movieData = new JSONObject(jsonResponse);

        // Obtener el ID de IMDb
        String imdbId = movieData.getString("imdb_id");
        movieData.put("imdb_id", imdbId);

        // Obtener el tráiler, la fecha de estreno y la duración
        String trailer = "";
        String releaseDate = movieData.getString("release_date");
        int duration = movieData.getInt("runtime");


        // Llamar a la API para obtener los videos (tráileres)
        String videosUrl = apiBaseUrl + "/movie/" + id + "/videos?api_key=" + apiKey;
        String videosResponse = fetchMovies(videosUrl);
        JSONObject videosData = new JSONObject(videosResponse);

        // Obtener el tráiler principal si existe
        JSONArray results = videosData.getJSONArray("results");
        if (results.length() > 0) {
            for (int i = 0; i < results.length(); i++) {
                JSONObject video = results.getJSONObject(i);
                if (video.getString("type").equals("Trailer")) {
                    trailer = video.getString("key");
                    break; // Salir del bucle al encontrar el primer tráiler
                }
            }
        }

        // Agregar los datos obtenidos a la respuesta de la película
        movieData.put("trailer", trailer);
        movieData.put("release_date", releaseDate);
        movieData.put("runtime", duration);

        // Llamar a la API para obtener el elenco
        String creditsUrl = apiBaseUrl + "/movie/" + id + "/credits?api_key=" + apiKey;
        String creditsResponse = fetchMovies(creditsUrl);
        JSONObject creditsData = new JSONObject(creditsResponse);

        // Agregar el elenco a la respuesta de la película
        movieData.put("cast", creditsData.getJSONArray("cast"));

        // Obtener el nombre del director
        String director = "Unknown";
        JSONArray crew = creditsData.getJSONArray("crew");
        for (int i = 0; i < crew.length(); i++) {
            JSONObject crewMember = crew.getJSONObject(i);
            if (crewMember.getString("job").equals("Director")) {
                director = crewMember.getString("name");
                break; // Salir del bucle cuando se encuentra al director
            }
        }
        // Agregar el nombre del director a la respuesta de la película
        movieData.put("director", director);

        // Llamar a la API para obtener imágenes de la película
        String imagesUrl = apiBaseUrl + "/movie/" + id + "/images?api_key=" + apiKey;
        String imagesResponse = fetchMovies(imagesUrl);
        JSONObject imagesData = new JSONObject(imagesResponse);

        // Obtener las imágenes y añadirlas a la respuesta
        JSONArray backdrops = imagesData.getJSONArray("backdrops");
        JSONArray posters = imagesData.getJSONArray("posters");

        // Crear un array para almacenar las URLs de las imágenes
        JSONArray imagesArray = new JSONArray();

        // Agregar las imágenes de backdrops (fondo)
        for (int i = 0; i < backdrops.length(); i++) {
            JSONObject backdrop = backdrops.getJSONObject(i);
            imagesArray.put(backdrop.getString("file_path")); // Almacenar solo la ruta
        }

        // Agregar las imágenes de posters
        for (int i = 0; i < posters.length(); i++) {
            JSONObject poster = posters.getJSONObject(i);
            imagesArray.put(poster.getString("file_path")); // Almacenar solo la ruta
        }

        // Agregar las imágenes a la respuesta de la película
        movieData.put("images", imagesArray);

        return movieData.toString(); // Devolver el objeto JSON completo
    }


    public String getSearchMovies(String query) {
        String url = apiBaseUrl + "/search/movie?language=en-US&query=" + query + "&api_key=" + apiKey;
        return fetchMovies(url);
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

    public MovieDetails getMovieDetails(int movieId) {
        String url = apiBaseUrl + "/movie/" + movieId + "?language=en-US&api_key=" + apiKey;
        String response = fetchMovies(url);

        try {
            JSONObject movieData = new JSONObject(response);
            MovieDetails movieDetails = new MovieDetails();
            movieDetails.setTitle(movieData.getString("title"));
            movieDetails.setPosterPath(movieData.getString("poster_path"));
            return movieDetails;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener detalles de la película", e);
        }
    }
}



