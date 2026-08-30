package com.example.DriveX.DTO;

import com.example.DriveX.Model.Bookings;

public class BookingRequestResponse {

    String message;
    Bookings bookings;

    public BookingRequestResponse(String message, Bookings bookings)
    {
        this.message = message;
        this.bookings = bookings;

    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {

        this.message = message;
    }

    public Bookings getBookings() {
        return bookings;
    }
    public void setBookings(Bookings bookings) {
        this.bookings = bookings;
    }

}
