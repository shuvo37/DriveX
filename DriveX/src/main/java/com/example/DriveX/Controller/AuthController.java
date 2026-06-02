package com.example.DriveX.Controller;

import com.example.DriveX.DTO.LoginRequest;
import com.example.DriveX.DTO.RegisterRequest;
import com.example.DriveX.DTO.loginResponse;
import com.example.DriveX.Model.User;
import com.example.DriveX.Repository.UserRepository;
import com.example.DriveX.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")

public class AuthController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @PostMapping("register")
    public ResponseEntity<?>register(@RequestBody RegisterRequest request)
    {

          try{

              User saved = authService.register(request);

              return ResponseEntity
                      .status(HttpStatus.CREATED)
                      .body(Map.of(

                        "message" , "Account created successfully" ,
                        "userId" , saved.getUserId()

                      ));

          } catch(RuntimeException e)
          {

               return ResponseEntity
                       .status(HttpStatus.BAD_REQUEST)
                       .body(Map.of(

                         "message" , e.getMessage()
                       ));


          }



    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request)
    {

          try{

              loginResponse user = authService.login(request);

              return ResponseEntity.ok(user);

          }catch(RuntimeException e)
          {

               return ResponseEntity
                       .status(HttpStatus.UNAUTHORIZED)
                       .body(Map.of("error" , e.getMessage()));

          }



    }




}
