package com.example.movieticketbookingspringboot.Service;

import com.example.movieticketbookingspringboot.Model.MovieData_aADSsa;
import org.springframework.stereotype.Service;

import java.util.List;
public interface MovieService_Serv {
    List<MovieData_aADSsa> findRecommendedMovies();

    MovieData_aADSsa getByID(Long id);
}
