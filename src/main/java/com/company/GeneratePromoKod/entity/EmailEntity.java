package com.company.GeneratePromoKod.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "email_history")
@Getter
@Setter

public class EmailEntity  {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "to_email")
    private String toEmail;
    @Column
    private String title;
    @Column
    private String content;
    @Column
    private LocalDateTime createDate= LocalDateTime.now();

}
