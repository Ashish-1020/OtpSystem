package com.example.Controller;


import com.example.Service.OtpService;
import com.example.dto.OtpVerificationResult;
import com.example.dto.SendOtpRequest;
import com.example.dto.VerifyOtpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody VerifyOtpRequest request) {

        OtpVerificationResult result =
                otpService.verifyOtp(request.getEmail(), request.getOtp());

        return switch (result) {

            case SUCCESS -> ResponseEntity.ok("OTP verified successfully");

            case OTP_NOT_FOUND -> ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("OTP not found or already used");

            case OTP_EXPIRED -> ResponseEntity
                    .status(HttpStatus.GONE)
                    .body("OTP expired");

            case INVALID_OTP -> ResponseEntity
                    .badRequest()
                    .body("Invalid OTP");
        };
    }


}
