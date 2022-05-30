package com.company.GeneratePromoKod.repository;

import com.company.GeneratePromoKod.entity.CurrentUser;
import com.company.GeneratePromoKod.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CurrenUserRepository extends JpaRepository<CurrentUser, Integer> {
    Optional<CurrentUser> findByUserId(String userId);
    void deleteByUserId(String userId);

    @Transactional
    @Modifying
    @Query("update CurrentUser set status = :status where userId=:id")
    int updateStatus(@Param("status")UserStatus status,@Param("id") String userId);

    @Transactional
    @Modifying
    @Query("update CurrentUser set status = :status ,email = :email where userId=:id")
    int updateStatus(@Param("status")UserStatus status,@Param("id") String userId,@Param("email") String email);

}