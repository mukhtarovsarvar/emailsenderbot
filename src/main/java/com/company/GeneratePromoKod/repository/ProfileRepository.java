package com.company.GeneratePromoKod.repository;

import com.company.GeneratePromoKod.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity,Integer> {


    Optional<ProfileEntity> findByUserId(String userId);
}
