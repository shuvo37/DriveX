package com.example.DriveX.DTO;

import com.example.DriveX.Model.Car;

public class UpdateCarResponse {

     private String message;

     private Car car;

     public UpdateCarResponse(String message ,  Car car)
     {

         this.message = message;
         this.car = car;

     }

     public String getMessage() {
         return message;
     }
     public Car getCar() {
         return car;
     }
     public void setMessage(String message) {
         this.message = message;
     }
     public void setCar(Car car) {
         this.car = car;
     }



}
