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

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

}
