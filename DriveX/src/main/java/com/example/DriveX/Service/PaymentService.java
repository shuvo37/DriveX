package com.example.DriveX.Service;


import com.example.DriveX.DTO.PaymentRequest;
import com.example.DriveX.Model.Car;
import com.example.DriveX.Model.Payment;
import com.example.DriveX.Model.User;
import com.example.DriveX.Repository.CarRepository;
import com.example.DriveX.Repository.PaymentRepository;
import com.example.DriveX.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentService {


    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CarRepository carRepository;


    public Payment payment(PaymentRequest paymentRequest) {

        User user = userRepository.findById(paymentRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("User " + paymentRequest.getUserId() + " not found"));

        Car car = carRepository.findById(paymentRequest.getCarId())
                .orElseThrow(() -> new RuntimeException("Car " + paymentRequest.getCarId() + " not found"));
             //  System.out.println("inside payment service");
        Payment payment = new Payment(car, user, paymentRequest.getPaymentMethod(),
                paymentRequest.getPaymentStatus(), paymentRequest.getPaymentType(),
                paymentRequest.getTotalAmount(), LocalDateTime.now());




        return paymentRepository.save(payment);

    }




}
