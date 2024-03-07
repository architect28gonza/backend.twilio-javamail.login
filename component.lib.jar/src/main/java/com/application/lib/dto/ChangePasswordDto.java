package com.application.lib.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChangePasswordDto {
    
    private String username;
    private String emailOrPhoneNumber;
    private boolean emailOrPhone;
}
