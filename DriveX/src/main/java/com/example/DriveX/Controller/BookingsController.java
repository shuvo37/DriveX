package com.example.DriveX.Controller;


import com.example.DriveX.DTO.BookingRequest;
import com.example.DriveX.DTO.BookingRequestResponse;
import com.example.DriveX.DTO.GetAllNotDeliveredCarBooking;
import com.example.DriveX.DTO.GetBookingOfCar;
import com.example.DriveX.Enums.BookingStatus;
import com.example.DriveX.Enums.RentalStatus;
import com.example.DriveX.Model.Bookings;
import com.example.DriveX.Model.Car;
import com.example.DriveX.Service.BookingsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingsController {

    @Autowired
    BookingsService bookingsService;

    @PostMapping
    public ResponseEntity<?> booking(@Valid @RequestBody BookingRequest bookingRequest){


        Bookings booking = bookingsService.booking(bookingRequest);

        BookingRequestResponse bookingRequestResponse = new BookingRequestResponse("successfully booked" , booking);

        return  ResponseEntity.ok(bookingRequestResponse);
    }

    @PostMapping("/get-booking-of-car")
    public ResponseEntity<?> getBookingOfCar(@Valid @RequestBody GetBookingOfCar getBookingOfCar)
    {

           Bookings bookings = bookingsService.getBookingOfCar(getBookingOfCar);

        return   ResponseEntity.ok(bookings);
    }

    @GetMapping("/get-all-not_delivered_car")
    public  ResponseEntity<?> getAllNotDeliveredCar()
    {

        List<Car> car = bookingsService.getAllCarOfNot_Delivered();

        return  ResponseEntity.ok(car);
    }

    @PostMapping("/get-all-bookings-not_delivered")
    public ResponseEntity<?> getAllBookingsNot_Delivered(@Valid @RequestBody GetAllNotDeliveredCarBooking getAllNotDeliveredCar)
    {
        System.out.println("getAllNotDeliveredCar " + getAllNotDeliveredCar.getModelName());

        Bookings bookings =  bookingsService.getAllBookingsNot_Delivered(getAllNotDeliveredCar);

        return  ResponseEntity.ok(bookings);

    }

    @GetMapping("/search-Booking-Car")
    public ResponseEntity<?> searchBookingCar(@RequestParam String modelName)
    {

        System.out.println("searchBookingCar " + modelName);

        Bookings booking = bookingsService.SearchBookingCar(modelName);


        return  ResponseEntity.ok(booking);

    }

    @GetMapping("all-booking-by-email")
    public ResponseEntity<?>getAllBookingsByEmail(@RequestParam String email)
    {

            List<Bookings> bookings =bookingsService.getAllBookingsByEmail(email);

        return   ResponseEntity.ok(bookings);

    }



    @PatchMapping("/set-rent-start-time/{bookingId}")
    public ResponseEntity<?> setRentStartTime(@PathVariable Long bookingId)
    {
        Bookings booking = bookingsService.setRentStartTime(bookingId);

        return  ResponseEntity.ok(booking);

    }

    @PatchMapping("/set-booking-car-submitted/{bookingId}")
    public ResponseEntity<?> setBookingCarSubmitted(@PathVariable Long bookingId)
    {
        Bookings booking = bookingsService.setBookingCarSubmitted(bookingId);

        return  ResponseEntity.ok(booking);

    }

    @PatchMapping("/set-rent-end/{bookingId}")
    public ResponseEntity<?> setRentEnd(@PathVariable Long bookingId)
    {
        Bookings booking = bookingsService.setRentEnd(bookingId);

        return  ResponseEntity.ok(booking);

    }


    @GetMapping("get-all-ongoing-cars")
    public ResponseEntity<?> getAllOngoingCars() {

        List<Car> car = bookingsService.getAllOngoingCars();


        return ResponseEntity.ok(car);

    }


}
