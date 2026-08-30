package com.example.DriveX.Service;

import com.example.DriveX.DTO.*;
import com.example.DriveX.Enums.Role;
import com.example.DriveX.Model.ConformationCode;
import com.example.DriveX.Model.User;
import com.example.DriveX.Repository.ConformationCodeRepository;
import com.example.DriveX.Repository.UserRepository;
import com.example.DriveX.config.JWT.JwtUtil;
import jakarta.transaction.Transactional;
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

    @Autowired
    private EmailService emailService;

    @Autowired
    private ConformationService conformationService;

    @Autowired
    private ConformationCodeRepository conformationCodeRepository;

    @Transactional
    public User register(RegisterRequest  registerRequest)
    {

        System.out.println("here registering");

        Optional<User>  user = userRepository.findByEmail(registerRequest.getEmail());


        if(user.isPresent())
        {

             User user1 = user.get();

             String password = user1.getPassword();

            if (password == null || password.isEmpty()) {
                userRepository.deleteByEmail(user1.getEmail());
                System.out.println("deleted stale row with no password");
            }
            else {
                throw new RuntimeException("Email already exist");
            }

        }



          if(registerRequest.getDateOfBirth().isAfter(LocalDate.now()))
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


          User newUser = new User(

                  registerRequest.getFirstName() ,

                  registerRequest.getLastName(),

                  registerRequest.getEmail(),

                  passwordEncoder.encode(registerRequest.getPassword()),

                  registerRequest.getPhoneNumber() ,

                  registerRequest.getDateOfBirth() ,

                  registerRequest.getProfileImage() ,

                  LocalDateTime.now() ,

                   Role.USER

          );

        newUser.setProfileComplete(true);

          return  userRepository.save(newUser);

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

        String token = jwtUtil.generateToken(user.getEmail() ,user.getUserId(),  user.getRole());

       /// System.out.println("Token generated: " + token);


       return new loginResponse(

               user.getUserId(),
               user.getFirstName() ,
               user.getLastName() ,
               user.getEmail(),
               user.getProfileImage(),
               user.getRole().toString(),
               token

       );

    }








    public User getUserByEmail(String email)
    {

          User user =  userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found in getUserByEmail"));

          return user;

    }

    public User UploadImage(String imgUrl , String email)
    {

        User user =  userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found in uploadimage"));
        user.setProfileImage(imgUrl);
        userRepository.save(user);
        return user;

    }


    @Transactional
    public User ForgetPasswordReset(String email, String code, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("user not found"));

         ConformationCode conformationCode = conformationCodeRepository
                 .findFirstByEmailAndIsUsedOrderByCreatedAtDesc(email , true)
                 .orElseThrow(() -> new RuntimeException("conformationCode not found"));

         if(!code.equals(conformationCode.getCode()))
         {
             throw new RuntimeException("code mismatch in reset");

         }

        if (newPassword.length() < 8) {
            throw new RuntimeException("Password too short");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return user;
    }




    public User RoleUpdate(String email , String role)
    {
           User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("user not found in roleupdate"));

        user.setRole(Role.valueOf(role));

        userRepository.save(user);

        return user;
    }


    public loginResponse completeProfile(CompleteProfileRequest request, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPhoneNumber(request.getPhoneNumber());
        user.setProfileComplete(true);

        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getEmail() ,user.getUserId() ,  user.getRole());

        return new loginResponse(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getProfileImage(),
                user.getRole().toString(),
                token
        );
    }


}
