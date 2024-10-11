package com.example.partidosya.dto;

import com.example.partidosya.models.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PopularMoviesResponse {
    private int page;
    private List<Movie> results;

}