package com.example.movieticketbookingspringboot.Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
@Entity
@Table(name="theaters")
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


@ManyToOne
private Booking boookings;
    private String name;
    private String location;
    @Column(name = "price_prime")
    private Integer price_prime;

    private String schedules;


}