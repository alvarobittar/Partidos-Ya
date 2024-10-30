package com.example.Storyloom.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Movie {

    // Atributos
    private String backdrop_path;
    private int id;
    private String title;
    private String original_title;
    private String overview;
    private String poster_path;
    private String media_type;
    private boolean adult;
    private String original_language;
    private List<Integer> genre_ids;
    private double popularity;
    private String release_date;
    private boolean video;

}
