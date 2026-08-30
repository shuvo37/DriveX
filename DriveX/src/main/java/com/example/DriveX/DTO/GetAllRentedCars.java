package com.example.DriveX.DTO;

import com.example.DriveX.Enums.RentalStatus;

public class GetAllRentedCars {


    private RentalStatus rentalStatus;

    public GetAllRentedCars(RentalStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
    }

    public GetAllRentedCars() {


    }

    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(RentalStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
    }


}
