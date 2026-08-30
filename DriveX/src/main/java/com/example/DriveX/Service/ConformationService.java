package com.example.DriveX.Service;

import com.example.DriveX.DTO.ForgetPasswordResponse;
import com.example.DriveX.DTO.ResendCodeResponse;
import com.example.DriveX.Model.ConformationCode;
import com.example.DriveX.Model.User;
import com.example.DriveX.Repository.ConformationCodeRepository;
import com.example.DriveX.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConformationService {

    @Autowired
    private ConformationCodeRepository conformationCodeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    private static final long BLOCK_WINDOW_MILLIS = 20 * 60 * 1000;

    private String generateCode() {
        SecureRandom random = new SecureRandom();
        int code = random.nextInt(10000); // range: 0-9999
        return String.format("%04d", code); // pads with leading zeros
    }

    @Transactional
    public ForgetPasswordResponse forgotPassword(String email) {

        Optional<User> user = userRepository.findByEmail(email);

        if (!user.isPresent()) {
            return new ForgetPasswordResponse(false, false, null, null);
        }

        User user1 = user.get();

        if (user1.getPassword().length() == 0) {
            return new ForgetPasswordResponse(true, false, null, null);
        }

        List<ConformationCode> recentCodes = conformationCodeRepository
                .findTop5ByEmailAndIsUsedOrderByCreatedAtDesc(email, false);

        if (recentCodes.size() == 5) {

            ConformationCode latest = recentCodes.get(0);
            ConformationCode fifthLatest = recentCodes.get(4);

            long diffMillis = Duration.between(fifthLatest.getCreatedAt(), LocalDateTime.now()).toMillis();

            if (diffMillis <= BLOCK_WINDOW_MILLIS) {

                //System.out.println("here here here here here!!!!!!!!");

                long blockedUntil = System.currentTimeMillis() + BLOCK_WINDOW_MILLIS;
                return new ForgetPasswordResponse(true, true, null, blockedUntil);
            }
        }

        String code = generateCode();

        ConformationCode conformationCode = new ConformationCode(user1.getEmail(), code);
        conformationCodeRepository.save(conformationCode);

        emailService.sendEmail(user1.getEmail(), "Confirmation code from DriveX",
                "Hi " + user1.getFullName() + " your Confirmation Code is: " + code);

        return new ForgetPasswordResponse(true, true, conformationCode.getExpiresAt(), null);
    }


    @Transactional
    public boolean ForgetPasswordVerifyCode(String email, String code) {

        Optional<User> user = userRepository.findByEmail(email);

        if (!user.isPresent()) {
            throw new RuntimeException("User not found");
        }

        User user1 = user.get();

        Optional<ConformationCode> most_recent_conformationCode = conformationCodeRepository
                .findFirstByEmailAndIsUsedOrderByCreatedAtDesc(email, false);

        if (!most_recent_conformationCode.isPresent()) {
            throw new RuntimeException("Conformation Code doesn't exist");
        }

        ConformationCode most_recent_conformationCode1 = most_recent_conformationCode.get();

        if (most_recent_conformationCode1.getExpiresAt() < System.currentTimeMillis()) {
            throw new RuntimeException("Conformation Code has expired");
        }

        if (most_recent_conformationCode1.getCode().equals(code)) {
            most_recent_conformationCode1.setIsUsed(true);
            conformationCodeRepository.save(most_recent_conformationCode1);
            return true;
        } else {
            return false;
        }
    }


    @Transactional
    public ResendCodeResponse ForgetPasswordResendCode(String email) {


        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("user not found"));

        List<ConformationCode> recentCodes = conformationCodeRepository
                .findTop5ByEmailAndIsUsedOrderByCreatedAtDesc(email, false);

        if (recentCodes.size() == 5) {

            ConformationCode latest = recentCodes.get(0);
            ConformationCode fifthLatest = recentCodes.get(4);

            long diffMillis = Duration.between(fifthLatest.getCreatedAt(), LocalDateTime.now()).toMillis();


            if (diffMillis <= BLOCK_WINDOW_MILLIS) {
                long blockedUntil = System.currentTimeMillis() + BLOCK_WINDOW_MILLIS;
                return new ResendCodeResponse(null , blockedUntil);
            }
        }

        String code = generateCode();

        ConformationCode conformationCode = new ConformationCode(user.getEmail(), code);
        conformationCodeRepository.save(conformationCode);

        emailService.sendEmail(user.getEmail(), "Confirmation code from DriveX",
                "Hi " + user.getFullName() + " your Confirmation Code is: " + code);

        return  new ResendCodeResponse(conformationCode.getExpiresAt() , null);


    }

}


