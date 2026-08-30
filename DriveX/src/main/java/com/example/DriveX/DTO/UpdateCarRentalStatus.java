package com.example.DriveX.DTO;

import com.example.DriveX.Enums.RentalStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class UpdateCarRentalStatus {

    @NotNull(message = "carId cannot be null")
    @Positive(message = "carId must be Positive")
    private Long carId;

    @NotNull(message = "rentalStatus cannot be null")
    private RentalStatus rentalStatus;


    public UpdateCarRentalStatus(Long carId , RentalStatus rentalStatus)
    {

         this.carId = carId;
         this.rentalStatus = rentalStatus;

    }

    public  UpdateCarRentalStatus()
    {
    }


    public Long getCarId() {
        return carId;
    }

    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }

   public void setRentalStatus(RentalStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
   }





}
