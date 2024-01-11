package com.example.movieticketbookingspringboot.Implementation;

import com.example.movieticketbookingspringboot.Model.Theater;
import com.example.movieticketbookingspringboot.Repository.TheaterRepository;
import com.example.movieticketbookingspringboot.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TheaterImplementation implements TheaterService {
    private TheaterRepository theaterRepository;
@Autowired
    public TheaterImplementation(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    @Override
    public Theater findAllById(Long id) {
        return theaterRepository.findById(id).orElse(null);
    }


    @Override
    public List<Theater> findAll() {
        return theaterRepository.findAll();
    }

    @Override
    public Theater fetchPrice(Long theaterId) {
        return theaterRepository.findPrice(theaterId);
    }
}
