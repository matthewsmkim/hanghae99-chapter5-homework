package com.hanghae99chapter5homework.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "{member.email.notblank}")
    private String email;

    @NotBlank(message = "{member.password.notblank}")
    private String password;
}
