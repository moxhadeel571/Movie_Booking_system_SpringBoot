package com.example.movieticketbookingspringboot.Controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserLRController {



    @GetMapping("/home")
    public String home(){
        return "about";
    }
    @GetMapping("/signin")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "Registration";
    }

   
    @GetMapping(path = "/error")
    public String getErrorMessage(){

        return "error";
    }

}
