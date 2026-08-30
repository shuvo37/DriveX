package com.example.DriveX.Model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "submission")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long submissionId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id" ,  nullable = false)
    private Payment payment;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id" , nullable = false)
    private Bookings booking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id" , nullable = false)
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid" , nullable = false)
    private User user;

    @Column(name = "submitted_at" , nullable = false , updatable = false)
    LocalDateTime submittedAt;

    public Submission() {

    }

    public Submission(Payment payment, Bookings booking, Car car, User user , LocalDateTime submittedAt) {
        this.payment = payment;
        this.booking = booking;
        this.car = car;
        this.user = user;
        this.submittedAt = submittedAt;
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public Payment getPayment() {
        return payment;
    }

    public void  setPayment(Payment payment) {
        this.payment = payment;
    }
    public Bookings getBooking() {
        return booking;
    }
    public void setBooking(Bookings booking) {
        this.booking = booking;
    }
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }
    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }


}
