package com.example.DriveX.Controller;


import com.example.DriveX.DTO.SubmitCar;
import com.example.DriveX.Model.Submission;
import com.example.DriveX.Repository.SubmissionRepository;
import com.example.DriveX.Service.SubmitCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/submission")
public class SubmissionController {


    @Autowired
    private SubmitCarService SubmitCarService;

    @PostMapping
    public ResponseEntity<?>  submit(@Valid @RequestBody SubmitCar submitCar){

           Submission submission =  SubmitCarService.AddSubmission(submitCar);

           return ResponseEntity.ok(submission);
    }


}
