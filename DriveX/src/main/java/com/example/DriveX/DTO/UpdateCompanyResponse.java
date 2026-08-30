package com.example.DriveX.DTO;

import com.example.DriveX.Model.Company;

public class UpdateCompanyResponse {

    private String message;
    private Company company;

    public UpdateCompanyResponse(String message, Company company) {
      this.message = message;
      this.company = company;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }

}
