package com.example.Service;

import com.example.Entity.EmailOtp;
import com.example.Repository.EmailOtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;

@Service
public class OtpService {



    @Autowired
    private EmailOtpRepository otpRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;



    public void generateAndSendOtp(String email) {


        String otp = String.valueOf(
                100000 + new SecureRandom().nextInt(900000)
        );


        String otpHash = passwordEncoder.encode(otp);


        EmailOtp entity = new EmailOtp();
        entity.setEmail(email);
        entity.setOtpHash(otpHash);
        entity.setExpiresAt(Instant.now().plusSeconds(300));
        entity.setUsed(false);

        otpRepository.save(entity);
        System.out.println("otp generated in service");


        emailService.sendOtp(email, otp);
    }
}
