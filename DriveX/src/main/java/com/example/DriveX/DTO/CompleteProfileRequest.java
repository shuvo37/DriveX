package com.example.DriveX.DTO;


import com.example.DriveX.Enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public class CompleteProfileRequest {


    @NotBlank(message = "phone number is required")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$" , message = "invalide phone number")
    private String phoneNumber;

    @NotNull(message = "Date of bith required")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Country is required")
    private String country;

    @NotBlank(message = "City is required")
    private String city;

    @NotNull(message =  "Gender is required")
    private Gender gender;


    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }






}
