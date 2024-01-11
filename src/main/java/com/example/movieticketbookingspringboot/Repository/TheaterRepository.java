package com.example.movieticketbookingspringboot.Repository;

import com.example.movieticketbookingspringboot.Model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    @Query("SELECT i.price_prime FROM Theater i WHERE i.id = :theaterId")
    Theater findPrice(@Param("theaterId") Long theaterId);
}

