package com.hanghae99chapter5homework.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String refreshToken;

    @NotBlank
    private String email;

    public RefreshToken(String token, String email){
        this.refreshToken = token;
        this.email = email;
    }

    public RefreshToken update(String token){
        this.refreshToken = token;
        return this;
    }

}
