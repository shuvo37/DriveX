package com.example.DriveX.Repository;

import com.example.DriveX.Model.ConformationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ConformationCodeRepository extends JpaRepository<ConformationCode, Long>
{

    Optional<ConformationCode> findFirstByEmailAndIsUsedOrderByCreatedAtDesc(String email, Boolean isUsed);

    List<ConformationCode>findTop5ByEmailAndIsUsedOrderByCreatedAtDesc(String email, Boolean isUsed);

}
