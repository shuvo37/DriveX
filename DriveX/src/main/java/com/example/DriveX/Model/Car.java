package com.example.DriveX.Model;

import com.example.DriveX.DTO.AddCar;
import com.example.DriveX.Enums.FuelType;
import com.example.DriveX.Enums.RentalStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long carId;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(name = "model_name", nullable = false , unique = true)
    private String modelName;

    @Column(name = "price_per_hour", nullable = false)
    private Double pricePerHour;

    @Enumerated(EnumType.STRING)
    @Column(name = "rental_status", nullable = false)
    private RentalStatus rentalStatus;

    @Column(name = "seats", nullable = false)
    private Integer seats;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel", nullable = false)
    private FuelType fuel;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "tag", nullable = true)
    private String tag;

    private boolean isActive;

    public Car() {}

    public Car(Company company, String modelName, Double pricePerHour, RentalStatus rentalStatus,
               Integer seats, FuelType fuel, String imageUrl, String tag , boolean isActive) {
        this.company = company;
        this.modelName = modelName;
        this.pricePerHour = pricePerHour;
        this.rentalStatus = rentalStatus;
        this.seats = seats;
        this.fuel = fuel;
        this.imageUrl = imageUrl;
        this.tag = tag;
        this.isActive = isActive;
    }


    public Long getCarId() { return carId; }
    public void setCarId(Long carId) { this.carId = carId; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }

    public String getModelName() { return modelName; }
    public void setModelName(String modelName) { this.modelName = modelName; }

    public Double getPricePerHour() { return pricePerHour; }
    public void setPricePerHour(Double pricePerHour) { this.pricePerHour = pricePerHour; }

    public RentalStatus getRentalStatus() { return rentalStatus; }
    public void setRentalStatus(RentalStatus rentalStatus) { this.rentalStatus = rentalStatus; }

    public Integer getSeats() { return seats; }
    public void setSeats(Integer seats) { this.seats = seats; }

    public FuelType getFuel() { return fuel; }
    public void setFuel(FuelType fuel) { this.fuel = fuel; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getTag() { return tag; }
    public void setTag(String tag) { this.tag = tag; }

    public boolean getIsActive() { return isActive; }
    public void setIsActive(boolean isActive) { this.isActive = isActive; }


}