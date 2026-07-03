package com.example.DriveX.Service;

import com.example.DriveX.DTO.CompleteProfileRequest;
import com.example.DriveX.DTO.LoginRequest;
import com.example.DriveX.DTO.RegisterRequest;
import com.example.DriveX.DTO.loginResponse;
import com.example.DriveX.Enums.Role;
import com.example.DriveX.Model.User;
import com.example.DriveX.Repository.UserRepository;
import com.example.DriveX.config.JWT.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public User register(RegisterRequest  registerRequest)
    {

          if(registerRequest.getEmail() == null ||userRepository.existsByEmail(registerRequest.getEmail()))
          {

               throw new RuntimeException("Email already exist");

          }

          if(registerRequest.getDateOfBirth() == null || registerRequest.getDateOfBirth().isAfter(LocalDate.now()))
          {

              throw new RuntimeException("Invalide Year");

          }

          long age = ChronoUnit.YEARS.between(registerRequest.getDateOfBirth() , LocalDate.now());

          if(age < 12)
          {

               throw new RuntimeException("Invalide Year");

          }

          if(registerRequest.getPassword() == null|| !registerRequest.getPassword().equals(registerRequest.getConfirmPassword()))
          {
              throw new RuntimeException("Passwords don't match");

          }

          if(registerRequest.getFirstName() == null || registerRequest.getLastName() == null)
          {

                throw new RuntimeException("name cannot be empty");

          }


        if(registerRequest.getPhoneNumber() == null || registerRequest.getPhoneNumber().length() != 11 ||
                registerRequest.getPhoneNumber().charAt(0) != '0' || registerRequest.getPhoneNumber().charAt(1) != '1') {
            throw new RuntimeException("invalid phone number");
        }

        boolean isValidPhone = registerRequest.getPhoneNumber().matches("\\d+");

        if(!isValidPhone) {
            throw new RuntimeException("invalid phone number");
        }


          User user = new User(

                  registerRequest.getFirstName() ,

                  registerRequest.getLastName(),

                  registerRequest.getEmail(),

                  passwordEncoder.encode(registerRequest.getPassword()),

                  registerRequest.getPhoneNumber() ,

                  registerRequest.getDateOfBirth() ,

                  registerRequest.getProfileImage() ,

                  LocalDateTime.now() ,

                  registerRequest.getCity() ,

                   Role.USER

          );

          user.setProfileComplete(true);

          return  userRepository.save(user);

    }


    public loginResponse login(LoginRequest loginRequest)
    {

     //  System.out.println("Login attempt: " + loginRequest.getEmail());

        User user = userRepository.findByEmail(loginRequest.getEmail()).
                orElseThrow(() -> new RuntimeException("Invalid email or password"));

        //System.out.println("User found: " + user.getEmail());
        //System.out.println("Stored password: " + user.getPassword());


        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword()))
        {

            throw new RuntimeException("Invalid email or password");

        }

       /// System.out.println("Password matched");
       // System.out.println("Generating token...");

        String token = jwtUtil.generateToken(user.getEmail() , user.getRole());

       /// System.out.println("Token generated: " + token);


       return new loginResponse(

               user.getUserId(),
               user.getFirstName() ,
               user.getLastName() ,
               user.getEmail(),
               user.getProfileImage()  ,
               token

       );

    }


    public loginResponse completeProfile(CompleteProfileRequest request, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPhoneNumber(request.getPhoneNumber());
        user.setCity(request.getCity());
        user.setProfileComplete(true);

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail() , user.getRole());

        return new loginResponse(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getProfileImage(),
                token
        );
    }


}
