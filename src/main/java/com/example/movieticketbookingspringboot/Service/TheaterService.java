package com.example.movieticketbookingspringboot.Service;

import com.example.movieticketbookingspringboot.Model.Theater;

import java.util.List;
import java.util.Optional;

public interface TheaterService {
    Theater findAllById(Long id);

    List<Theater> findAll();

    Theater fetchPrice(Long theaterId);
}
