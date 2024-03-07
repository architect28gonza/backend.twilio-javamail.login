package com.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "tbl_user", schema = "she_auth")
@Data
@NamedQueries({
        @NamedQuery(name = "UserEntity.findByUsername", query = "SELECT u FROM UserEntity u WHERE u.username = :username"),
        @NamedQuery(name = "UserEntity.findByUsernameAndEmail", query = "SELECT COUNT(u) FROM UserEntity u WHERE u.username = :username AND u.email = :email"),
        @NamedQuery(name = "UserEntity.findByUsernameAndPhone", query = "SELECT COUNT(u) FROM UserEntity u WHERE u.username = :username AND u.phone = :phone")
})
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "use_id")
    private Long id;

    @Column(name = "use_username", nullable = false, length = 100)
    @JsonProperty("usuario")
    private String username;

    @Column(name = "use_password", nullable = false, length = 100)
    @JsonProperty("contrasena")
    private String password;

    @Column(name = "use_email", nullable = false, length = 100)
    @JsonProperty("correo")
    private String email;

    @Column(name = "use_phone", nullable = false, length = 30)
    @JsonProperty("telefono")
    private String phone;

    @Column(name = "use_role", nullable = false, length = 20)
    @JsonProperty("rol")
    private String role;

    @Column(name = "use_register", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;
    
    @PrePersist
    protected void onCreate() {
        registerDate = new Date(); // Establece la fecha actual justo antes de persistir
    }


    public UserEntity() {
    }
}
