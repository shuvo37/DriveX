package com.example.DriveX.Service;


import com.example.DriveX.DTO.AddCompany;
import com.example.DriveX.DTO.UpdateCompany;
import com.example.DriveX.DTO.UpdateCompanyActiveStatus;
import com.example.DriveX.Enums.RentalStatus;
import com.example.DriveX.Model.Company;
import com.example.DriveX.Repository.CarRepository;
import com.example.DriveX.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CarRepository carRepository;

    public Company addCompany(AddCompany addNewCompany){


            if(companyRepository.existsByCompanyEmail(addNewCompany.getCompanyEmail()))
            {

                 throw new RuntimeException("Email already exist");

            }

            if(addNewCompany.getCompanyName().isEmpty() ||companyRepository.existsByCompanyName(addNewCompany.getCompanyName()))
            {
                throw new RuntimeException("Company name already exist or any problem");
            }

            String companyPhone = addNewCompany.getCompanyPhoneNumber();

            if (companyPhone == null || companyPhone.length() != 11 ||
                    companyPhone.charAt(0) != '0' || companyPhone.charAt(1) != '1' ||
                    !companyPhone.chars().allMatch(ch -> ch >= '0' && ch <= '9')) {

                   throw new RuntimeException("Invalid phone number");
            }

            if(addNewCompany.getCompanyAddress().isEmpty())
            {

                throw new RuntimeException("Company address is empty");


            }
            if(addNewCompany.getCompanyCity().isEmpty())
            {
                throw new RuntimeException("Company city is empty");
            }


            Company company = new Company(

                    addNewCompany.getCompanyName(),
                    addNewCompany.getCompanyEmail(),
                    addNewCompany.getCompanyPhoneNumber(),
                    addNewCompany.getCompanyCity() ,
                    addNewCompany.getCompanyAddress() ,
                    true

            );

          return  companyRepository.save(company);

    }

    public List<Company>getAllCompanies()
    {
         List<Company> companies = companyRepository.findAll();

         companies.removeIf(company -> !company.getIsActive());

         return companies;
    }

    public List<Company>getAllCompanyThatAllCarOfCompanyInMaintenance()
    {
        List<Company> rentedOrAvailableCarOfThatCompany = carRepository.findDistinctCompaniesByRentalStatusIn(List.of(RentalStatus.RENTED ,
                RentalStatus.AVAILABLE));


        List<Company> companies = companyRepository.findAll();

        List<Company> maintainanceCarOfThatCompany = new ArrayList<Company>();

        Set<Long> excludedIds = new HashSet<>();

        for(Company company : rentedOrAvailableCarOfThatCompany)
        {
            excludedIds.add(company.getCompanyId());

        }

        for(Company company : companies)
        {

            if(!excludedIds.contains(company.getCompanyId()))
            {
                maintainanceCarOfThatCompany.add(company);
            }

        }



     return maintainanceCarOfThatCompany;

    }


    public Company UpdateCompany(Long companyID , UpdateCompany updateCompany)
    {

       Company company = companyRepository.findById(companyID).
                         orElseThrow(() -> new RuntimeException("Company not found"));

        if(!company.getCompanyName().equals(updateCompany.getCompanyName()))
        {

              if(companyRepository.existsByCompanyName(updateCompany.getCompanyName()))
              {

                  throw new RuntimeException("Company name already exist");
              }

              company.setCompanyName(updateCompany.getCompanyName());

        }

        if(!company.getCompanyEmail().equals(updateCompany.getCompanyEmail()))
        {

            if(companyRepository.existsByCompanyEmail(updateCompany.getCompanyEmail()))
            {

                throw new RuntimeException("Company email already exist");
            }

            company.setCompanyEmail(updateCompany.getCompanyEmail());

        }

        if(!company.getCompanyAddress().equals(updateCompany.getCompanyAddress()))
        {

            company.setCompanyAddress(updateCompany.getCompanyAddress());
        }

        if(!company.getCompanyCity().equals(updateCompany.getCompanyCity()))
        {
            company.setCompanyCity(updateCompany.getCompanyCity());
        }

        if(!company.getCompanyPhoneNumber().equals(updateCompany.getCompanyPhoneNumber()))
        {

            company.setCompanyPhoneNumber(updateCompany.getCompanyPhoneNumber());

        }


        return companyRepository.save(company);
    }

    public Company UpdateCompanyActiveStatus(UpdateCompanyActiveStatus companyInfo)
    {

          Company company = companyRepository.findByCompanyName(companyInfo.getCompanyName()).
                  orElseThrow(() -> new RuntimeException("Company not found"));

          company.setIsActive(companyInfo.getIsActive());

        return  companyRepository.save(company);

    }



}
