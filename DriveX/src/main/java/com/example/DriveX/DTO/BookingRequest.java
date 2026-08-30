package com.example.DriveX.DTO;

import com.example.DriveX.Model.Car;
import com.example.DriveX.Model.Payment;
import com.example.DriveX.Model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class BookingRequest {

    @NotNull(message = "userId is required")
    @Positive(message = "userId must be positive")
    private Long userId;

    @NotNull(message = "paymentId is required")
    @Positive(message = "paymentId must be positive")
    private Long paymentId;

    @NotNull(message = "carId is required")
    @Positive(message = "carId must be positive")
    private Long carId;

    @NotNull(message = "totalprice is required")
    @Positive(message = "totalprice must be positive")
    private Double totalprice;

    @NotNull(message = "days is required")
    @PositiveOrZero(message = "days cannot be negative")
    private Integer days;

    @NotNull(message = "hour is required")
    @Min(value = 0, message = "hour must be between 0 and 23")
    @Max(value = 23, message = "hour must be between 0 and 23")
    private Integer hour;

    @NotNull(message = "totalHours is required")
    @Positive(message = "totalHours must be positive")
    private Integer totalHours;


    @NotBlank(message = "address must not be blank")
    private String address;

    @NotBlank(message = "phone must not be blank")
    private String phone;

    public BookingRequest() {
    }

    public BookingRequest(Long userId, Long paymentId, Long carId, Double totalprice,
                          Integer days, Integer hour, Integer totalHours,
                           String address, String phone) {
        this.userId = userId;
        this.paymentId = paymentId;
        this.carId = carId;
        this.totalprice = totalprice;
        this.days = days;
        this.hour = hour;
        this.totalHours = totalHours;
        this.address = address;
        this.phone = phone;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
