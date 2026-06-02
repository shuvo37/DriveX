package com.example.DriveX.Service;

import com.example.DriveX.DTO.LoginRequest;
import com.example.DriveX.DTO.RegisterRequest;
import com.example.DriveX.DTO.loginResponse;
import com.example.DriveX.Model.User;
import com.example.DriveX.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

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


          if(registerRequest.getCountry() == null)
          {

              throw new RuntimeException("ciuntry name should not be empty");

          }

          User user = new User(

                  registerRequest.getFirstName() ,

                  registerRequest.getLastName(),

                  registerRequest.getEmail(),

                  registerRequest.getPassword(),

                  registerRequest.getPhoneNumber() ,

                  registerRequest.getDateOfBirth() ,

                  registerRequest.getProfileImage() ,

                  registerRequest.getCreatedAt() ,

                  registerRequest.getCountry() ,

                  registerRequest.getCity() ,

                  registerRequest.getGender() ,

                  registerRequest.getRole()

          );

          return  userRepository.save(user);

    }


    public loginResponse login(LoginRequest loginRequest)
    {

        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());

        if(user.isEmpty())
        {
            throw new RuntimeException("User not found");

        }

       User user1 = user.get();

       String password = loginRequest.getPassword();

       if(!password.equals(user1.getPassword()))
       {
           throw new RuntimeException("Invalid password");
       }

       return new loginResponse(

               user1.getUserId(),
               user1.getFirstName() ,
               user1.getLastName() ,
               user1.getEmail(),
               user1.getProfileImage()

       );



    }


}
