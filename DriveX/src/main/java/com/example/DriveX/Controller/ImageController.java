package com.example.DriveX.Controller;

import com.example.DriveX.Model.User;
import com.example.DriveX.Repository.UserRepository;
import com.example.DriveX.Service.AuthService;
import com.example.DriveX.config.CloudImageHandler.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private AuthService authService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String url = cloudinaryService.uploadImage(file);
        System.out.println("successfully fetch url " + url);
        return ResponseEntity.ok(url);
    }

    @PostMapping("/upload-image")
    public ResponseEntity<?> uploadImage(@RequestParam String imageUrl , @RequestParam String email){

        User user = authService.UploadImage(imageUrl , email);

        return ResponseEntity.ok(user);

    }
}