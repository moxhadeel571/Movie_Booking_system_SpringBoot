package com.example.movieticketbookingspringboot.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "movies")
public class MovieData_aADSsa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Boolean adult;

    private String backdropPath;
    private Long budget;
    private String homepage;
    private String imdbId;
    private String originalLanguage;

    @Column(name = "original_title")
    private String originalTitle;

    private String overview;
    private Double popularity;
    private String posterPath;

    @OneToMany( cascade = CascadeType.ALL)
    private List<Booking> bookings;
}
