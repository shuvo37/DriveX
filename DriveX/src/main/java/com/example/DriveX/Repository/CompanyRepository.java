package com.example.DriveX.Repository;

import com.example.DriveX.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

      Optional<Company> findByCompanyEmail(String companyEmail);

      boolean existsByCompanyEmail(String companyEmail);

      boolean existsByCompanyName(String companyName);

      Optional<Company> findByCompanyName(String companyName);



}
