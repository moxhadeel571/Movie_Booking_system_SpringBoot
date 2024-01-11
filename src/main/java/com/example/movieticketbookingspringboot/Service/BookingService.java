package com.example.movieticketbookingspringboot.Service;

import com.example.movieticketbookingspringboot.Model.Booking;
import org.springframework.data.crossstore.ChangeSetPersister;

public interface BookingService {
    Booking findById(Long id) throws ChangeSetPersister.NotFoundException;

    void saveBooking(Booking booking);

    Booking save(Booking booking);
}
