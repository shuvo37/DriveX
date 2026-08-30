package com.example.DriveX.DTO;

import com.example.DriveX.Model.Bookings;
import com.example.DriveX.Model.Payment;

public class PaymentRequestResponse {

    String message;

    Payment payment;

    public PaymentRequestResponse(String message, Payment payment)
    {
        this.message = message;
        this.payment = payment;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Payment getPayment() {
        return payment;
    }
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

}
