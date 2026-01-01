package com.example.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendOtp(String email,String otp){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your otp for Gate Pass");
        message.setText("Your OTP is: " + otp + "\nValid for 5 minutes.");
        System.out.println("email send in EmailService to "+email);


        mailSender.send(message);

    }
}
