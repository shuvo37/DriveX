package com.example.DriveX.Service;


import com.example.DriveX.DTO.SubmitCar;
import com.example.DriveX.Model.*;
import com.example.DriveX.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SubmitCarService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingsRepository bookingsRepository;


    public Submission AddSubmission(SubmitCar submitCar) {


        Payment payment = paymentRepository.findByPaymentId(submitCar.getPaymentId())
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        Bookings bookings = bookingsRepository.findByBookingId(submitCar.getBookingId())
                .orElseThrow(() -> new RuntimeException("Bookings not found"));

        Car car = carRepository.findById(submitCar.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        User user = userRepository.findById(submitCar.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));



        Submission submission = new Submission(payment , bookings , car , user , LocalDateTime.now());

        Submission  saved =  submissionRepository.save(submission);

        emailService.sendEmail(
                saved.getUser().getEmail(),
                "Car submission confirmed",
                "Hi " + saved.getUser().getFullName() + ",\n\nWe've received your car ("
                        + saved.getCar().getModelName() + ") at " + saved.getSubmittedAt() + ". Thanks!"
        );

         return  saved;

    }




}






