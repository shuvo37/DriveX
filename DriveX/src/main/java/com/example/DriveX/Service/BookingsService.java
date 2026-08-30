package com.example.DriveX.Service;
import com.example.DriveX.DTO.BookingRequest;
import com.example.DriveX.DTO.GetAllNotDeliveredCarBooking;
import com.example.DriveX.DTO.GetBookingOfCar;
import com.example.DriveX.Enums.BookingStatus;
import com.example.DriveX.Model.Bookings;
import com.example.DriveX.Model.Car;
import com.example.DriveX.Model.Payment;
import com.example.DriveX.Model.User;
import com.example.DriveX.Repository.BookingsRepository;
import com.example.DriveX.Repository.CarRepository;
import com.example.DriveX.Repository.PaymentRepository;
import com.example.DriveX.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingsService {


       @Autowired
       private PaymentRepository paymentRepository;

       @Autowired
       private UserRepository userRepository;

       @Autowired
       private BookingsRepository bookingsRepository;

       @Autowired
       CarRepository carRepository;

        @Autowired
        private EmailService emailService;



       public Bookings booking(BookingRequest bookingRequest) {


           User user = userRepository.findById(bookingRequest.getUserId())
                   .orElseThrow(() -> new RuntimeException("User " + bookingRequest.getUserId() + " not found"));

           Car car = carRepository.findById(bookingRequest.getCarId())
                   .orElseThrow(() -> new RuntimeException("Car " + bookingRequest.getCarId() + " not found"));


           Payment payment = paymentRepository.findById(bookingRequest.getPaymentId())
                   .orElseThrow(() -> new RuntimeException("Payment " + bookingRequest.getPaymentId() + " not found"));



           Bookings bookings = new Bookings(user , payment , car  ,  bookingRequest.getTotalprice() ,
                       LocalDateTime.now() , bookingRequest.getDays() , bookingRequest.getHour() ,
                   bookingRequest.getTotalHours() ,
                   bookingRequest.getAddress() , bookingRequest.getPhone() , BookingStatus.NOT_DELIVERED);


            return bookingsRepository.save(bookings);

       }



       public  Bookings getBookingOfCar(GetBookingOfCar getBookingOfCar)
       {

             User user = userRepository.findByEmail(getBookingOfCar.getEmail()).
                     orElseThrow(() -> new RuntimeException("User " + getBookingOfCar.getEmail() + " not found in booking"));

             Long userId = user.getUserId();

              Bookings bookings = bookingsRepository.findByCar_CarIdAndBookingStatus(
                            getBookingOfCar.getCarId(), BookingStatus.ONGOING).
                         orElseThrow(()-> new RuntimeException("Booking " + getBookingOfCar.getCarId() + " not found"));

            return bookings;
       }

       public Bookings SearchBookingCar(String modelName)
       {

           Optional<Bookings> wrapBooing = bookingsRepository.findByCar_ModelNameAndBookingStatus(modelName ,  BookingStatus.ONGOING);

           if(wrapBooing.isPresent())
           {
               return wrapBooing.get();
           }

          return null;


       }

       public List<Car> getAllCarOfNot_Delivered()
       {
             List<Bookings> bookings = bookingsRepository.findByBookingStatus(BookingStatus.NOT_DELIVERED);

             List<Car> cars = new ArrayList<>();

             for (Bookings booking : bookings) {

                  cars.add(booking.getCar());
             }

             return cars;
       }

       public List<Car>getAllOngoingCars()
       {

           List<Bookings> bookings = bookingsRepository.findByBookingStatus(BookingStatus.ONGOING);

           List<Car> cars = new ArrayList<>();

           for (Bookings booking : bookings) {

               cars.add(booking.getCar());
           }

           return cars;

       }

       public Bookings getAllBookingsNot_Delivered(GetAllNotDeliveredCarBooking getAllNotDeliveredCar)
       {

            Bookings bookings = bookingsRepository.findByCar_ModelNameAndBookingStatus(
                    getAllNotDeliveredCar.getModelName() , BookingStatus.NOT_DELIVERED)
                    .orElseThrow(()->new RuntimeException(" not car's  booking that is not delivered"));

           return bookings;

       }

       public List<Bookings> getAllBookingsByEmail(String email)
       {

           User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User " + email + " not  in " +
                   "getAllBookingsByEmai"));

           List<Bookings> bookings = bookingsRepository.findByUser_UserId(user.getUserId());

           return bookings;

       }

       public Bookings setRentStartTime(Long bookingId)
       {

           Bookings bookings = bookingsRepository.findByBookingId(bookingId).orElseThrow(()->new RuntimeException("Booking id not found"));

             bookings.setRentStart(LocalDateTime.now());
             bookings.setToggle(true);
             bookings.setBookingStatus(BookingStatus.ONGOING);

           Bookings saved =  bookingsRepository.save(bookings);

          emailService.sendEmail(
                   saved.getUser().getEmail(),
                   "Your rental has started",
                   "Hi " + saved.getUser().getFullName() + ",\n\nYour rental for "
                           + saved.getCar().getModelName() + " started at " + saved.getRentStart() + "."
           );

           return saved;
       }

    public Bookings setRentEnd(Long bookingId)
    {

        Bookings bookings = bookingsRepository.findByBookingId(bookingId).orElseThrow(()->new RuntimeException("Booking id not found"));

        bookings.setRentEnd(LocalDateTime.now());
        bookings.setToggle(false);

        return  bookingsRepository.save(bookings);
    }


    public Bookings setBookingCarSubmitted(Long bookingId)
    {

        Bookings bookings = bookingsRepository.findByBookingId(bookingId).orElseThrow(()->new RuntimeException("Booking id not found"));

        bookings.setBookingStatus(BookingStatus.SUBMITTED);

        return  bookingsRepository.save(bookings);
    }


}
