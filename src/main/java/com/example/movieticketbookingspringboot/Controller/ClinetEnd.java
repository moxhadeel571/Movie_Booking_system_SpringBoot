package com.example.movieticketbookingspringboot.Controller;

import com.example.movieticketbookingspringboot.Model.Booking;
import com.example.movieticketbookingspringboot.Model.MovieData_aADSsa;
import com.example.movieticketbookingspringboot.Model.Theater;
import com.example.movieticketbookingspringboot.Service.BookingService;
import com.example.movieticketbookingspringboot.Service.EmailService;
import com.example.movieticketbookingspringboot.Service.MovieService_Serv;
import com.example.movieticketbookingspringboot.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
;

// ...


import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Controller
@RequestMapping("/Client")
public class ClinetEnd {
private EmailService emailService;

    private MovieService_Serv movieServiceServ;
    private BookingService bookingService;
    private TheaterService theaterService;
@Autowired
    public ClinetEnd(EmailService emailService, MovieService_Serv movieServiceServ, BookingService bookingService, TheaterService theaterService) {
    this.emailService = emailService;
    this.movieServiceServ = movieServiceServ;
    this.bookingService = bookingService;
    this.theaterService = theaterService;
}

    @GetMapping("/Dashboard")
    public String GetDasboard(Model model){
//        List<MovieData_aADSsa> movieDataAADSsas=movieServiceServ.findRecommendedMovies();
//        model.addAttribute("recommend",movieDataAADSsas);
        return "Dashboard";
    }
    @GetMapping("/View_DAvadadw/{id}")
    public String getView(@PathVariable("id")String id,Model model){
        System.out.println("here is id :" +id);
        Long movieId = Long.parseLong(id);
        model.addAttribute("id",movieId);
        System.out.println("here is id :" +id);
        System.out.println("here is id :" +id);
        System.out.println("here is id :" +id);
        System.out.println("here is id :" +id);
//    MovieData_aADSsa movieDataAADSsa=movieServiceServ.getByID(id);
        return "ViewMovie";

}
    @GetMapping("/Buy_biIaba/{id}")
    public String GetTIcket(Model model, @PathVariable Long id) {
        List<Theater> theaters = theaterService.findAll();
        System.out.println(" here is the id :"+id);
        // Fetch theater data by id


        System.out.println("Before finding theater by ID");
        System.out.println("After finding theater by ID");


        model.addAttribute("theaters", theaters);
        model.addAttribute("idticket", id);
        return "BuyingForm";
    }
    @GetMapping("/theater_Seating/{idticket}/theater/{theaterId}/schedule/{schedule}")
    public String getTheaterSeatingPage(
            @PathVariable Long idticket,
            @PathVariable Long theaterId,
            @PathVariable String schedule,
            Model model
    ) throws ChangeSetPersister.NotFoundException {
        // Fetch theater details
        Optional<Theater> theaterOptional = Optional.ofNullable(theaterService.findAllById(theaterId));

        if (theaterOptional.isPresent()) {
            Theater theater = theaterOptional.get();
            Theater price = theaterService.fetchPrice(theaterId);

            Integer price2 = (price != null) ? price.getPrice_prime() : 250;
            Booking booking = bookingService.findById(theaterId);
            Integer Booking = booking.getTotalPrice();
            model.addAttribute("booking", booking);
            model.addAttribute("price2", price2);
            model.addAttribute("price", price);
            model.addAttribute("theater", theater);
            model.addAttribute("id", idticket);
            model.addAttribute("schedule", schedule);

            return "Seating";
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }


    @GetMapping("/FinalForm/{idticket}/theater/{theaterId}/schedule/{schedule}")
    public String getFinal(
            @PathVariable Long idticket,
            @PathVariable Long theaterId,
            @PathVariable String schedule,
            @RequestParam(name = "seats", required = false, defaultValue = "0") int seats,
            @RequestParam(name = "amount", required = false, defaultValue = "0") int amount,

            Model model
    ) throws ChangeSetPersister.NotFoundException {
        // Fetch theater details
        Theater theater = theaterService.findAllById(theaterId);
        Theater price = theaterService.fetchPrice(theaterId);
        Integer price2 = (price != null) ? price.getPrice_prime() : 250;
        model.addAttribute("price2", price2);  // Default value is 0, change it to your preference
        model.addAttribute("theaterId", theaterId);  // Default value is 0, change it to your preference
        model.addAttribute("seats", seats);
        model.addAttribute("amount", amount);
        model.addAttribute("theater", theater);
        model.addAttribute("id", idticket);
        model.addAttribute("schedule", schedule);

        return "Final";
    }
    @PostMapping("/payment/success/{idticket}/theater/{theaterId}/schedule/{schedule}")
    public String saved(@ModelAttribute Booking booking,
                        @PathVariable Long idticket,
                        @PathVariable Long theaterId,
                        @PathVariable String schedule,
                        @RequestParam(name = "seats", required = false, defaultValue = "0") int seats,
                        @RequestParam(name = "amount", required = false, defaultValue = "0") int amount,
                        Model model) {

        // Fetch theater details
        Theater theater = theaterService.findAllById(theaterId);
        Theater price = theaterService.fetchPrice(theaterId);

        // Default price if theater or price is null
        Integer price2 = (price != null) ? price.getPrice_prime() : 250;

        // Set attributes in the model
        model.addAttribute("price2", price2);
        model.addAttribute("theaterId", theaterId);
        model.addAttribute("seats", seats);
        model.addAttribute("amount", amount);
        model.addAttribute("theater", theater);
        model.addAttribute("id", idticket);
        model.addAttribute("schedule", schedule);

            // Save the booking and get the savedBooking with the generated id
           bookingService.save(booking);

        // Redirect to the Dashboard
        return "redirect:/Client/Dashboard";
    }




}
