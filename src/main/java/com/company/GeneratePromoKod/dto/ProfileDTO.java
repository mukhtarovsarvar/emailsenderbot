package com.company.GeneratePromoKod.dto;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Setter
@Getter
public class ProfileDTO {
    private Integer id;
    private String userId;
    private String name;
    private LocalDateTime startedDate = LocalDateTime.now();

    public ProfileDTO(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public ProfileDTO() {
    }
}

