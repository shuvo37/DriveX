package com.example.DriveX.Controller;

import com.example.DriveX.DTO.PaymentRequest;
import com.example.DriveX.DTO.PaymentRequestResponse;
import com.example.DriveX.Model.Payment;
import com.example.DriveX.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {


    @Autowired
    PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> payment(@RequestBody PaymentRequest  paymentRequest)
    {

        System.out.println("paymentRequest............" + paymentRequest);

        Payment payment = paymentService.payment(paymentRequest);

        PaymentRequestResponse paymentRequestResponse = new PaymentRequestResponse("successfully payment" , payment);

        System.out.println("inside payment controller");

        return ResponseEntity.ok(paymentRequestResponse);

    }


}
