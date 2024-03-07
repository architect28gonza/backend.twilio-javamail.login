package com.application.lib.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class MessageDto {

    private String message;
    
    private String status;

}
