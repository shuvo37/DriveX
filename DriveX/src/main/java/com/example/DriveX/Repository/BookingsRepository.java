package com.example.DriveX.Repository;
import com.example.DriveX.Enums.BookingStatus;
import com.example.DriveX.Model.Bookings;
import com.example.DriveX.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingsRepository extends JpaRepository<Bookings, Long> {


       Optional<Bookings> findByCar_CarIdAndBookingStatus(Long id , BookingStatus bookingStatus);

       List<Bookings>findByBookingStatus(BookingStatus bookingStatus);

       Optional<Bookings> findByCar_ModelName(String modelName);

       Optional<Bookings> findByBookingId(Long id);

       Optional<Bookings> findByCar_ModelNameAndBookingStatus(String modelName ,  BookingStatus BookingStatus);

       List<Bookings>findByUser_UserId(Long id);
}
