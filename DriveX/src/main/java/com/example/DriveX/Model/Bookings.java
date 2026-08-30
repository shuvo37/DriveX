package com.example.DriveX.Model;

import com.example.DriveX.Enums.BookingStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id" , nullable = false)
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(name = "total_price", nullable = false)
    private Double totalprice;

    @Column(name = "booking_time", nullable = false, updatable = false)
    private LocalDateTime bookingTime;

    @Column(name = "days", nullable = false)
    private Integer days;

    @Column(name = "hour", nullable = false)
    private Integer hour;

    @Column(name = "total_hours", nullable = false)
    private Integer totalHours;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status")
    private BookingStatus bookingStatus;

    private LocalDateTime rentStart;

    private LocalDateTime rentEnd;

    private boolean toggle;

    public Bookings() {

    }

    public Bookings(User user, Payment payment , Car car, Double totalprice, LocalDateTime bookingTime,
                    Integer days, Integer hour, Integer totalHours,
                    String address, String phone , BookingStatus bookingStatus) {
        this.user = user;
        this.payment = payment;
        this.car = car;
        this.totalprice = totalprice;
        this.bookingTime = bookingTime;
        this.days = days;
        this.hour = hour;
        this.totalHours = totalHours;
        this.address = address;
        this.phone = phone;
        this.bookingStatus = bookingStatus;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public LocalDateTime getRentStart()
    {
        return rentStart;
    }

    public void setRentStart(LocalDateTime rentStart) {
        this.rentStart = rentStart;
    }

    public LocalDateTime getRentEnd()
    {
        return rentEnd;
    }
    public void setRentEnd(LocalDateTime rentEnd) {
        this.rentEnd = rentEnd;
    }

    public  boolean getToggle() {
        return toggle;
    }
    public void setToggle(boolean toggle) {
        this.toggle = toggle;
    }
}