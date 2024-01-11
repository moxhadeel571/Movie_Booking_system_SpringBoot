package com.example.movieticketbookingspringboot.Repository;

import com.example.movieticketbookingspringboot.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {
}
