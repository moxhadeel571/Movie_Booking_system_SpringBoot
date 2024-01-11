package com.example.movieticketbookingspringboot.Model;

import jakarta.persistence.*;
import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name="bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seats;
    private String beverages;
    private Integer price;
    private Integer TotalPrice;
    private String mail;
    @ManyToOne
    private Theater theater;
    private String username;
    @ManyToOne
    @JoinColumn
    private MovieData_aADSsa movie;
}
