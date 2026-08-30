package com.example.DriveX.Repository;

import com.example.DriveX.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User ,Long>
{

      Optional<User>findByEmail(String email);

      boolean existsByEmail(String email);

      long deleteByEmail(String email);



}
