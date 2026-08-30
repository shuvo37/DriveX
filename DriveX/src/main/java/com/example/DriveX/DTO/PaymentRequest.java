package com.example.DriveX.DTO;

import com.example.DriveX.Enums.PaymentMethod;
import com.example.DriveX.Enums.PaymentStatus;
import com.example.DriveX.Enums.PaymentType;
import com.example.DriveX.Model.Car;
import com.example.DriveX.Model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PaymentRequest {


    @NotNull(message = "carId is required")
    @Positive(message = "carId must be positive")
    private Long carId;

    @NotNull(message = "userId is required")
    @Positive(message = "userId must be positive")
    private Long userId;

    @NotNull(message = "paymentMethod is required")
    private PaymentMethod paymentMethod;

    @NotNull(message = "paymentStatus is required")
    private PaymentStatus paymentStatus;

    @NotNull(message = "paymentType is required")
    private PaymentType paymentType;

    @NotNull(message = "totalAmount is required")
    @Positive(message = "totalAmount must be positive")
    private Double totalAmount;

    public PaymentRequest() {
    }

    public PaymentRequest(Long carId, Long userId, PaymentMethod paymentMethod, PaymentStatus paymentStatus,
                          PaymentType paymentType, Double totalAmount) {
        this.carId = carId;
        this.userId = userId;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentType = paymentType;
        this.totalAmount = totalAmount;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

}
