package com.example.DriveX.DTO;

import com.example.DriveX.Enums.FuelType;
import com.example.DriveX.Enums.RentalStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class UpdateCar {

    @NotBlank(message = "company name required")
    private String companyName;

    @NotBlank(message = "modelName can't be blank")
    private String modelName;

    @NotNull(message = "pricePerHour is require")
    @Positive(message = "price must be positive")
    private Double pricePerHour;

    @NotNull(message = "rentalStatus can't be blank")
    private RentalStatus rentalStatus;

    @NotNull(message = "sears  is required")
    @Min(value = 1  , message  = "seats must at least one")
    private Integer seats;

    @NotNull(message = "fuel can't be blank")
    private FuelType fuel;

    @NotBlank(message = "url can't be blank")
    private String imageUrl;

    private String tag;


    public UpdateCar( String companyName   ,String modelName, Double pricePerHour, RentalStatus rentalStatus ,
                   Integer seats  , FuelType fuel, String imageUrl, String tag) {
        this.companyName = companyName;
        this.modelName = modelName;
        this.pricePerHour = pricePerHour;
        this.rentalStatus = rentalStatus;
        this.seats = seats;
        this.fuel = fuel;
        this.imageUrl = imageUrl;
        this.tag = tag;

    }

    public UpdateCar() {}


    public String getCompanyName() {
        return companyName;
    }
    public String getModelName() {
        return modelName;
    }
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
    public Double getPricePerHour() {
        return pricePerHour;
    }
    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }
    public void setRentalStatus(RentalStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
    }
    public Integer getSeats() {
        return seats;
    }
    public void setSeats(Integer seats) {
        this.seats = seats;
    }
    public FuelType getFuel() {
        return fuel;
    }
    public void setFuel(FuelType fuel) {
        this.fuel = fuel;
    }
    public String getImageUrl() {

        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }





}
