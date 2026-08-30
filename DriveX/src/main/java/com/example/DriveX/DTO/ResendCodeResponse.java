package com.example.DriveX.DTO;

public class ResendCodeResponse {

    private Long expiresAt;    // set when code was sent successfully
    private Long blockedUntil; // set when blocked

    public ResendCodeResponse() {
    }

    public ResendCodeResponse(Long expiresAt, Long blockedUntil) {
        this.expiresAt = expiresAt;
        this.blockedUntil = blockedUntil;
    }

    public Long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Long getBlockedUntil() {
        return blockedUntil;
    }

    public void setBlockedUntil(Long blockedUntil) {
        this.blockedUntil = blockedUntil;
    }
}