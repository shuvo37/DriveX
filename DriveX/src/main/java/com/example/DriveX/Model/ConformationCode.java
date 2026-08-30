package com.example.DriveX.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "conformation_code")
public class ConformationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "code", nullable = false, updatable = false)
    private String code;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // epoch millis — matches JS Date.now(), no timezone conversion needed on the frontend
    @Column(name = "expires_at", nullable = false)
    private Long expiresAt;

    @Column(name = "is_used")
    private Boolean isUsed = false;

    // code is valid for 3 minutes
    private static final long VALIDITY_MILLIS = 3 * 60 * 1000;

    // JPA requires a no-arg constructor
    public ConformationCode() {
    }

    public ConformationCode(String email, String code) {
        this.email = email;
        this.code = code;
        this.createdAt = LocalDateTime.now();

        long createdAtMillis = this.createdAt
                .atZone(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli();
        this.expiresAt = createdAtMillis + VALIDITY_MILLIS;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Boolean getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() >= this.expiresAt;
    }
}