package com.application.lib.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginDto {

    @JsonProperty("usuario")
    private String username;

    @JsonProperty("contrasena")
    private String password;
}
