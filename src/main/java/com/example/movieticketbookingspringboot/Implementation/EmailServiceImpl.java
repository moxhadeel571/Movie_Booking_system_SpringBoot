package com.example.movieticketbookingspringboot.Implementation;



import com.example.movieticketbookingspringboot.Model.Booking;
import com.example.movieticketbookingspringboot.Repository.BookingRepository;
import com.example.movieticketbookingspringboot.Service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements EmailService {
  @Autowired
  private BookingRepository bookingRepository;

  private final JavaMailSender javaMailSender;
  private final TemplateEngine templateEngine;

  @Autowired
  public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine) {
    this.javaMailSender = javaMailSender;
    this.templateEngine = templateEngine;
  }

  @Override
  public void sendBillEmail(Long id, String recipientEmail) {
    Booking bookingDetails = bookingRepository.findById(id).orElse(null);

    if (bookingDetails != null) {
      // Generate HTML content using Thymeleaf
      String htmlContent = generateBillHtml(id);

      // Send email with HTML content
      sendEmail(recipientEmail, "Bill", htmlContent);
    }
  }

  private void sendEmail(String to, String subject, String htmlContent) {
    try {
      MimeMessage message = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message, true);

      helper.setTo(to);
      helper.setSubject(subject);
      helper.setText(htmlContent, true); // Set to true for HTML content

      javaMailSender.send(message);
    } catch (MessagingException e) {
      e.printStackTrace();
      // Handle exception
    }
  }

  private String generateBillHtml(Long id) {
    // Use Thymeleaf template engine to process the HTML template
    Context context = new Context();
    context.setVariable("bookingDetails", bookingRepository.findById(id).orElse(null));

    return templateEngine.process("/resources/templates/Bill.html", context);
  }
}
