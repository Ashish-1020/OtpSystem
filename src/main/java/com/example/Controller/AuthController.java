package com.example.Controller;


import com.example.Service.OtpService;
import com.example.dto.SendOtpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private OtpService otpService;


    @PostMapping("/get-otp")
    public ResponseEntity<String> getOtp(@RequestBody SendOtpRequest request){
        otpService.generateAndSendOtp(request.getEmail());
        System.out.println("request came from"+request.getEmail());

        return ResponseEntity.ok("OTP sent successfully");
    }

}
