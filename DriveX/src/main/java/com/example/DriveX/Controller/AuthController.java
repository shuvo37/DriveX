package com.example.DriveX.Controller;

import com.example.DriveX.DTO.CompleteProfileRequest;
import com.example.DriveX.DTO.LoginRequest;
import com.example.DriveX.DTO.RegisterRequest;
import com.example.DriveX.DTO.loginResponse;
import com.example.DriveX.Model.User;
import com.example.DriveX.Repository.UserRepository;
import com.example.DriveX.Service.AuthService;
import com.example.DriveX.config.JWT.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")

public class AuthController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?>register( @Valid @RequestBody RegisterRequest request)
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


   // @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request)
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

    @PostMapping("/complete-profile")
    public ResponseEntity<?> completeProfile(@Valid @RequestBody CompleteProfileRequest request,
                                             @RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.substring(7);

            String email = jwtUtil.extractEmail(token);
            loginResponse response = authService.completeProfile(request, email);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }




}
