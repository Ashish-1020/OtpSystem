package com.example.Repository;

import com.example.Entity.EmailOtp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmailOtpRepository extends JpaRepository<EmailOtp, UUID> {
    Optional<EmailOtp> findTopByEmailAndUsedFalseOrderByCreatedAtDesc(String email);
}
