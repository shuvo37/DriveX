package com.example.DriveX.Controller;
import com.example.DriveX.DTO.*;
import com.example.DriveX.Model.Company;
import com.example.DriveX.Repository.CompanyRepository;
import com.example.DriveX.Service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping
    public ResponseEntity<?> addCompany(@Valid @RequestBody AddCompany carInfo)
    {
        Company company =   companyService.addCompany(carInfo);
        CreatedCompanyResponse createdCompanyResponse = new CreatedCompanyResponse("successfully added new company" , company);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompanyResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAllCompanies()
    {

         List<Company> companies = companyService.getAllCompanies();
         return ResponseEntity.ok(companies);

    }

    @GetMapping("/compayThatAllCarInMaintainance")
    public ResponseEntity<?>CompayThatAllCarInMaintainance()
    {
        List<Company> companies = companyService.getAllCompanyThatAllCarOfCompanyInMaintenance();

        return ResponseEntity.ok(companies);

    }


    @PutMapping("/{companyId}")
    public ResponseEntity<?>updateCompany(@PathVariable  Long companyId , @Valid @RequestBody UpdateCompany companyInfo)
    {

          Company company = companyService.UpdateCompany(companyId , companyInfo);

          UpdateCompanyResponse updateCompanyResponse = new UpdateCompanyResponse("successfully updated company" , company);

          return  ResponseEntity.status(HttpStatus.OK).body(updateCompanyResponse);

    }

    @PatchMapping("/company-active-status-update")
    public ResponseEntity<?> updateCompanyActiveStatus(@Valid @RequestBody UpdateCompanyActiveStatus companyInfo)
    {

         Company company = companyService.UpdateCompanyActiveStatus(companyInfo);

         UpdateCompanyActiveStatusResponse updateCompanyActiveStatusResponse =
                 new UpdateCompanyActiveStatusResponse("successfully updated company" , company);


         return  ResponseEntity.status(HttpStatus.OK).body(updateCompanyActiveStatusResponse);

    }


}
