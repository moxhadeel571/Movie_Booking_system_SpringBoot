package com.example.movieticketbookingspringboot.Repository;

import com.example.movieticketbookingspringboot.Model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email,Long> {
}
