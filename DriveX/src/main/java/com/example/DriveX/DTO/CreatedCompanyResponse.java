package com.example.DriveX.DTO;

import com.example.DriveX.Model.Company;

public class CreatedCompanyResponse {


    private String messagee;
    private Company company;

    public CreatedCompanyResponse(String messagee, Company company) {
        this.messagee = messagee;
        this.company = company;
    }

    public String getMessagee() {
        return messagee;
    }
    public void setMessagee(String messagee) {
        this.messagee = messagee;
    }

    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        this.company = company;
    }


}
