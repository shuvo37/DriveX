package com.example.DriveX.Repository;

import com.example.DriveX.Model.Bookings;
import com.example.DriveX.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {


    Optional<Payment> findByPaymentId(Long paymentId);


}
