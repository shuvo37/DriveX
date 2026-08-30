package com.example.DriveX.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class SubmitCar {

    @NotNull(message = "paymentId cannot be null")
    @Positive(message = "paymentId must be positive")
    private Long paymentId;

    @NotNull(message = "bookingId cannot be null")
    @Positive(message = "bookingId must be positive")
    private Long bookingId;

    @NotNull(message = "carId cannot be null")
    @Positive(message = "carId must be positive")
    private Long carId;

    @NotNull(message = "userId cannot be null")
    @Positive(message = "userId must be positive")
    private Long userId;

    @NotNull(message = "submittedAt cannot be null")
    private LocalDateTime submittedAt;

    public SubmitCar() {
    }

    public SubmitCar(Long paymentId, Long bookingId, Long carId, Long userId, LocalDateTime submittedAt) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.carId = carId;
        this.userId = userId;
        this.submittedAt = submittedAt;
    }

    public Long getPaymentId() {
        return paymentId;
    }


    public Long getBookingId() {
        return bookingId;
    }


    public Long getCarId() {
        return carId;
    }


    public Long getUserId() {
        return userId;
    }


    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

}