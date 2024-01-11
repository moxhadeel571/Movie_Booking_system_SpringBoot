package com.example.movieticketbookingspringboot.Repository;

import com.example.movieticketbookingspringboot.Model.MovieData_aADSsa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository_Jpa extends JpaRepository<MovieData_aADSsa, Long> {
}
