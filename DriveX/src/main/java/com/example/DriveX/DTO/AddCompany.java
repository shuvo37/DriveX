package com.example.DriveX.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class AddCompany {

    @NotBlank(message = "company name is required")
    private String companyName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String companyEmail;

    @NotBlank(message = "phone number is required")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$" , message = "invalide phone number")
    private String companyPhoneNumber;

    @NotBlank(message = "company address is required")
    private String companyAddress;

    @NotBlank(message = "company city is required")
    private String companyCity;

    public AddCompany(String companyName, String companyEmail, String companyPhoneNumber, String companyAddress, String companyCity) {
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyPhoneNumber = companyPhoneNumber;
        this.companyAddress = companyAddress;
        this.companyCity = companyCity;
    }


    public String getCompanyName() {
        return companyName;
     }

     public void setCompanyName(String companyName) {
        this.companyName = companyName;
     }

     public String getCompanyEmail() {
        return companyEmail;
     }
     public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
     }
     public String getCompanyPhoneNumber() {
        return companyPhoneNumber;
     }
     public void setCompanyPhoneNumber(String companyPhone) {
        this.companyPhoneNumber = companyPhone;
     }
     public String getCompanyAddress() {
        return companyAddress;
     }
     public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
     }
     public String getCompanyCity() {
        return companyCity;
     }
     public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
     }


}
