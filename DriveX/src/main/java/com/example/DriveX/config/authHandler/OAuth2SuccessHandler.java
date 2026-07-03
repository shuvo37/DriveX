package com.example.DriveX.config.authHandler;


import com.example.DriveX.Enums.Gender;
import com.example.DriveX.Enums.Role;
import com.example.DriveX.Model.User;
import com.example.DriveX.Repository.UserRepository;
import com.example.DriveX.config.JWT.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class OAuth2SuccessHandler  implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getAttribute("email");
        String firstName = oAuth2User.getAttribute("given_name");
        String lastName = oAuth2User.getAttribute("family_name");
        String picture = oAuth2User.getAttribute("picture");

        Optional<User> existing = userRepository.findByEmail(email);

        User user;
        if (existing.isPresent()) {
            user = existing.get();
        } else {
            // Save partial user — only what Google gives us
            user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword("");
            user.setProfileImage(picture);
            user.setCreatedAt(LocalDateTime.now());
            user.setRole(Role.USER);
            user.setProfileComplete(false);
            userRepository.save(user);
        }

        String token = jwtUtil.generateToken(user.getEmail() , user.getRole());

        if (!user.isProfileComplete()) {
            // Redirect to complete profile page with token
            response.sendRedirect("http://localhost:5173/complete-profile?token=" + token);
        } else {
            // Fully logged in
            response.sendRedirect("http://localhost:5173/oauth2/callback?token=" + token);
        }
    }
}