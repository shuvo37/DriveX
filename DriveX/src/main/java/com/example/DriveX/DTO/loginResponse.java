package com.example.DriveX.DTO;

import com.example.DriveX.Enums.Role;

public class loginResponse {


     private Long userId;
     private String firstName;
     private String lastName;
     private String email;
     private String profileImage;
     private String token;
     private String role;
     public loginResponse() {}

     public loginResponse(Long userId, String firstName, String lastName, String email, String profileImage ,String role ,  String token) {

         this.userId = userId;
         this.firstName = firstName;
         this.lastName = lastName;
         this.email = email;
         this.profileImage = profileImage;
         this.role = role;
         this.token = token;
     }

     public Long getUserId() {
         return userId;
     }
     public String getFirstName() {

         return firstName;
     }

     public String getLastName() {
         return lastName;
     }
     public String getEmail() {
         return email;
     }
     public String getProfileImage() {
         return profileImage;
     }
     public String getRole(){return role.toString();}
     public String getToken() {
         return token;
     }

}


