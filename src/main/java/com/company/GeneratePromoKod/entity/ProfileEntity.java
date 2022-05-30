package com.company.GeneratePromoKod.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ProfileEntity {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)
    private String userId;
    @Column
    private String name;

    @Column
    private LocalDateTime startedDate = LocalDateTime.now();
}
