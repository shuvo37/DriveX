package com.example.DriveX.DTO;

import com.example.DriveX.Model.Car;

public class AddCarResponse {

    String message;
    Car car;

    public AddCarResponse(String message, Car car) {
        this.message = message;
        this.car = car;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }

}
