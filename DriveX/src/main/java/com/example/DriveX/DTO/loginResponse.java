package com.example.DriveX.DTO;

public class loginResponse {


     private Long userId;
     private String firstName;
     private String lastName;
     private String email;
     private String profileImage;

     public loginResponse(Long userId, String firstName, String lastName, String email, String profileImage) {

         this.userId = userId;
         this.firstName = firstName;
         this.lastName = lastName;
         this.email = email;
         this.profileImage = profileImage;
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

}


