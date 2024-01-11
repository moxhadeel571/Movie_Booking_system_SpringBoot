package com.example.movieticketbookingspringboot.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "mails")
public class Email {
    @Id
    private String from;
    private String to;
    private String subject;
    private String body;
}
