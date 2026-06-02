package com.example.DriveX.DTO;

import com.example.DriveX.Enums.Gender;
import com.example.DriveX.Enums.Role;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String profileImage;
    private LocalDateTime createdAt;
    private String country;
    private String city;
    private Gender gender;
    private Role role;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;

    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;

    }

    public String getProfileImage() {
        return profileImage;
    }
    public void setProfileImage(String profileImage) {this.profileImage = profileImage;}

    public LocalDateTime getCreatedAt() {return createdAt;}

    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}


    public String getCountry() {
        return country;

    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {return  city;}
    public void setCity(String city) {this.city = city;}

    public Gender getGender() {return gender;}
    public void setGender(Gender gender) {this.gender = gender;}
    public Role getRole() {return role;}
    public void setRole(Role role) {this.role = role;}

}