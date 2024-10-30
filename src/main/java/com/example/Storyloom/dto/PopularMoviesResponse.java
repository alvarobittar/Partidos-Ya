package com.example.Storyloom.dto;

import com.example.Storyloom.models.Movie;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PopularMoviesResponse {
    private int page;
    private List<Movie> results;

}