package com.example.DriveX.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GetBookingOfCar {

    @NotNull(message = "carId cannot Null")
    Long carId;

    @NotBlank(message = "email cannot be blank")
    @Email(message = "Invalid image format")
    String email;


    public GetBookingOfCar(Long carId, String email) {
        this.carId = carId;
        this.email = email;
    }

    public GetBookingOfCar() {
    }

    public Long  getCarId() {
        return carId;
    }

    public String getEmail() {
        return email;
    }





}
