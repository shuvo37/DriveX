package com.example.DriveX.DTO;

public class ForgetPasswordResponse {

    private boolean exists;

    private boolean hasPassword;

    private Long expiresAt;

    private Long blockUntill;

    public ForgetPasswordResponse() {

    }

    public ForgetPasswordResponse(boolean exists, boolean hasPassword, Long expiresAt ,Long blockUntill) {
        this.exists = exists;
        this.hasPassword = hasPassword;
        this.expiresAt = expiresAt;
        this.blockUntill = blockUntill;
    }

    public boolean getExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }

    public boolean getHasPassword() {
        return hasPassword;
    }

    public void setHasPassword(boolean hasPassword) {
        this.hasPassword = hasPassword;
    }

    public Long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Long getBlockUntill() {
        return blockUntill;
    }
    public void setBlockUntill(Long blockUntill) {
        this.blockUntill = blockUntill;
    }

}
