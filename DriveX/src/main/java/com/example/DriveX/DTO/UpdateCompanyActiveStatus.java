package com.example.DriveX.DTO;

import jakarta.validation.constraints.NotBlank;

public class UpdateCompanyActiveStatus {

    @NotBlank(message = "company name is required")
    private String companyName;

    boolean isActive;

    public UpdateCompanyActiveStatus(String companyName , boolean isActive)
    {
        this.companyName = companyName;
        this.isActive = isActive;
    }

    public String getCompanyName()
    {
        return companyName;
    }
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
    public boolean getIsActive()
    {
        return isActive;
    }

    public void setIsActive(boolean isActive)
    {
        this.isActive = isActive;
    }

}
