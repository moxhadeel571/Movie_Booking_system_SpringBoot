package com.example.movieticketbookingspringboot.Service;



import com.example.movieticketbookingspringboot.Model.Email;
import jakarta.mail.MessagingException;


public interface EmailService {

    void sendBillEmail(Long id, String recipientEmail);
}
