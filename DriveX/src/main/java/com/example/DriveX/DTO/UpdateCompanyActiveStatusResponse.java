package com.example.DriveX.DTO;

import com.example.DriveX.Model.Company;

public class UpdateCompanyActiveStatusResponse {

    private  String message;
    private Company Company;

    public UpdateCompanyActiveStatusResponse(String message , Company Company)
        {
        this.message = message;
        this.Company = Company;
        }

        public String getMessage() {
           return message;
        }
        public void setMessage(String message) {
           this.message = message;
        }
        public Company getCompany() {
           return Company;
        }
        public void setCompany(Company Company) {
           this.Company = Company;
        }

}
