package com.application.lib.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    @JsonProperty("usuario")
    private String username;
    
    @JsonProperty("contrasena")
    private String password;

    @JsonProperty("rol")
    private String role;

    @JsonProperty("correo")
    private String email;

    @JsonProperty("telefono")
    private String phone;
    
}
