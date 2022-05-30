package com.company.GeneratePromoKod.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SendMessageToEmailDTO {
    @Email(message = "email hato")
    private String email;
    @NotBlank(message = "title hato")
    private String title;
    @NotBlank(message = "content hato")
    private String content;

}
