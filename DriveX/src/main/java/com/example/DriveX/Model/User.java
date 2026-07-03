package com.example.DriveX.Model;

import com.example.DriveX.Enums.Gender;
import com.example.DriveX.Enums.Role;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email")
        }
)
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "user_id")
   private Long userId;

   @Column(name = "first_name", nullable = false)
   private String firstName;

   @Column(name = "last_name", nullable = false)
   private String lastName;

   @Column(nullable = false, unique = true)
   private String email;

   @Column(nullable = false)
   private String password;

   @Column(name = "phone_number")
   private String phoneNumber;

   @Column(name = "date_of_birth")
   private LocalDate dateOfBirth;

   @Column(name = "profile_image")
   private String profileImage;

   @Column(name = "created_at", updatable = false)
   private LocalDateTime createdAt;

   @Column(name = "city" , nullable = true)
   private String city;

   @Enumerated(EnumType.STRING)
   @Column(name = "role")
   private Role role;

   @Column(name = "is_profile_complete" , nullable = false)
   private boolean isProfileComplete;

   public User() {}

   public User(String firstName , String lastName , String email , String password ,
               String phoneNumber , LocalDate dateOfBirth , String profileImage ,
               LocalDateTime createdAt , String city, Role role)
   {

          this.firstName = firstName;
          this.lastName = lastName;
          this.email = email;
          this.password = password;
          this.phoneNumber = phoneNumber;
          this.dateOfBirth = dateOfBirth;
          this.profileImage = profileImage;
          this.createdAt = createdAt;
          this.city = city;
          this.role = role;

   }

   // Getters
   public Long getUserId() { return userId; }
   public String getFirstName() { return firstName; }
   public String getLastName() { return lastName; }
   public String getEmail() { return email; }
   public String getPassword() { return password; }
   public String getPhoneNumber() { return phoneNumber; }
   public LocalDate getDateOfBirth() { return dateOfBirth; }
   public String getProfileImage() { return profileImage; }
   public LocalDateTime getCreatedAt() { return createdAt; }
   public String getCity() { return city; }
   public Role getRole() { return role; }
   public boolean isProfileComplete() { return isProfileComplete; }

   // Setters
   public void setUserId(Long userId) { this.userId = userId; }
   public void setFirstName(String firstName) { this.firstName = firstName; }
   public void setLastName(String lastName) { this.lastName = lastName; }
   public void setEmail(String email) { this.email = email; }
   public void setPassword(String password) { this.password = password; }
   public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
   public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
   public void setProfileImage(String profileImage) { this.profileImage = profileImage; }
   public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
   public void setCity(String city) { this.city = city; }
   public void setRole(Role role) { this.role = role; }
   public void setProfileComplete(boolean profileComplete) { isProfileComplete = profileComplete; }
}