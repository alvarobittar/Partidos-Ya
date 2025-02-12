package com.example.Storyloom.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movielist")
public class MovieList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private int movieId;

    private String title;
    private String posterPath;


    @Enumerated(EnumType.STRING)
    private MovieStatus status;

    public enum MovieStatus {
        WATCHLIST, SEEN
    }
}




