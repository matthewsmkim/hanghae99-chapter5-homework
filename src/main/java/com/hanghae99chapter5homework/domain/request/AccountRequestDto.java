package com.hanghae99chapter5homework.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDto {

    @NotBlank(message = "{member.email.notblank}")
    @Email(message = "{member.email.form}")
    private String email;

    @NotBlank(message = "{member.nickname.notblank}")
    @Size(min = 4, max = 12, message = "{member.nickname.size}")
    @Pattern(regexp = "[a-z\\d]*${4,12}", message = "{member.nickname.pattern}")
    private String nickname;

    @NotBlank(message = "{member.password.notblank}")
    @Size(min = 8, max = 20, message = "{member.password.size}")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$", message = "{member.password.pattern}")
    private String password;

    @NotBlank
    private String checkPassword;


}
