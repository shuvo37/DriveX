package com.example.DriveX.Controller;

import com.example.DriveX.DTO.*;
import com.example.DriveX.Enums.Role;
import com.example.DriveX.Model.ConformationCode;
import com.example.DriveX.Model.User;
import com.example.DriveX.Repository.UserRepository;
import com.example.DriveX.Service.AuthService;
import com.example.DriveX.Service.ConformationService;
import com.example.DriveX.config.JWT.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ConformationService conformationService;

    @PostMapping("/register")
    public ResponseEntity<?>register( @Valid @RequestBody RegisterRequest request)
    {

              System.out.println("sending "+ request);

              User saved = authService.register(request);



              return ResponseEntity
                      .status(HttpStatus.CREATED)
                      .body(Map.of(

                        "message" , "Account created successfully" ,
                        "userId" , saved.getUserId()

                      ));

    }

    @PatchMapping("/role-update")
    public ResponseEntity<?> updateRole(@RequestParam String email, @RequestParam String role)
    {
          User user = authService.RoleUpdate(email ,role);

          return ResponseEntity.ok(user);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request)
    {

              loginResponse user = authService.login(request);

              return ResponseEntity.ok(user);
    }

    @GetMapping("/get-user-by-email")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email)
    {

        User user = authService.getUserByEmail(email);

        return ResponseEntity.ok(user);

    }

    @PostMapping("/forgot-password/check-email")
    public ResponseEntity<ForgetPasswordResponse> forgetPasswordCheckEmail(@RequestBody Map<String, String> request)
    {
        String email = request.get("email");

        ForgetPasswordResponse conformationCode = conformationService.forgotPassword(email);


         return ResponseEntity.ok(conformationCode);

    }

    @PostMapping("/forgot-password/verify-code")
    public ResponseEntity<?> forgetPasswordVerifyCode(@RequestBody Map<String, String> request)
    {
        String email = request.get("email");
        String code = request.get("code");

        boolean ok = conformationService.ForgetPasswordVerifyCode(email , code);



        return ResponseEntity.ok(ok);

    }

    @PostMapping("/forgot-password/resend-code")
    public ResponseEntity<?> forgetPasswordResendCode(@RequestBody Map<String, String> request)
    {
         String email = request.get("email");

         ResendCodeResponse resendCodeResponse  = conformationService.ForgetPasswordResendCode(email);

        return ResponseEntity.ok(resendCodeResponse);

    }

    @PostMapping("/forgot-password/reset")
    public ResponseEntity<?> forgetPasswordReset(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String code = request.get("code");
        String newPassword = request.get("newPassword");

         System.out.println("sending "+ email + " "+ code + " "+ newPassword);

        User user = authService.ForgetPasswordReset(email, code, newPassword);

        return ResponseEntity.ok(true);
    }




    @PostMapping("/complete-profile")
    public ResponseEntity<?> completeProfile(@Valid @RequestBody CompleteProfileRequest request,
                                             @RequestHeader("Authorization") String authHeader) {
            String token = authHeader.substring(7);

            String email = jwtUtil.extractEmail(token);
            loginResponse response = authService.completeProfile(request, email);
            return ResponseEntity.ok(response);

    }




}
