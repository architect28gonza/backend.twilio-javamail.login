package com.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InitSessionDto {
    
    private String message;

    private String token;
    
    private String status;
}
