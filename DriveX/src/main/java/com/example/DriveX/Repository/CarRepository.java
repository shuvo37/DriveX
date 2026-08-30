package com.example.DriveX.Repository;

import com.example.DriveX.Enums.RentalStatus;
import com.example.DriveX.Model.Car;
import com.example.DriveX.Model.Company;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

      boolean existsByModelName(String modelName);
      Optional<Car> findByModelName(String modelName);
      Optional<Car>findById(Long id);
      List<Car> findByRentalStatus(RentalStatus rentalStatus);

      @Query("SELECT DISTINCT c.company FROM Car c WHERE c.rentalStatus IN :statuses")
      List<Company> findDistinctCompaniesByRentalStatusIn(@Param("statuses") List<RentalStatus> statuses);

}
