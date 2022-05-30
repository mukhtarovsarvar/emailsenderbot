package com.company.GeneratePromoKod.dto;

import com.company.GeneratePromoKod.enums.UserStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailSendDTO {
    private String email;
    private UserStatus status;
}
