package com.example.movieticketbookingspringboot.Implementation;

import com.example.movieticketbookingspringboot.Model.Booking;
import com.example.movieticketbookingspringboot.Repository.BookingRepository;
import com.example.movieticketbookingspringboot.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Component
public class BookingImplementation implements BookingService {

    private BookingRepository bookingRepository;
@Autowired
    public BookingImplementation(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }



    @Override
    public Booking findById(Long id) {
        // Use orElseThrow to handle the case where the booking is not found
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Booking not found with id: " + id));
    }

    @Override
    public void saveBooking(Booking booking) {
        // Implement the logic to save the booking using the repository
        bookingRepository.save(booking);
    }

    @Override
    public Booking save(Booking booking) {

        return booking;
    }
}
