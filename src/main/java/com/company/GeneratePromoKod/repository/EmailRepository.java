package com.company.GeneratePromoKod.repository;

import com.company.GeneratePromoKod.entity.EmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailEntity,Integer> {


  /*
    @Query(value = "update email_history set activate_date = :time where id=:id")
    boolean updateActivateDate(@Param("time")LocalDateTime activateDate, @Param("id") String id);
*/

}
