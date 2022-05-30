package com.company.GeneratePromoKod.entity;

import com.company.GeneratePromoKod.enums.UserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "current_status")
@NoArgsConstructor
@ToString
public class CurrentUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String userId;
    @Column
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    @Column
    private LocalDateTime time = LocalDateTime.now();
    @Column
    private String email;


    public CurrentUser(String userId, UserStatus status) {
        this.userId = userId;
        this.status = status;
    }

    public CurrentUser(String userId, UserStatus status, String email) {
        this.userId = userId;
        this.status = status;
        this.email = email;
    }
}
