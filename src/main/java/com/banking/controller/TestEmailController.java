package com.banking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.banking.service.EmailService;

@RestController
@RequestMapping("/test")
public class TestEmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/email")
    public String sendTestEmail() {
        emailService.sendEmail("phanindrab8@gmail.com", "Test Subject", "This is a test email from Spring Boot!");
        return "Email sent successfully!";
    }
}
