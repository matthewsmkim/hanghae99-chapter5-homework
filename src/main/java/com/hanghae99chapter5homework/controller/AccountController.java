package com.hanghae99chapter5homework.controller;

import com.hanghae99chapter5homework.domain.request.LoginRequestDto;
import com.hanghae99chapter5homework.domain.request.AccountRequestDto;
import com.hanghae99chapter5homework.service.AccountService;
import com.hanghae99chapter5homework.global.GlobalResDto;
import com.hanghae99chapter5homework.jwt.JwtUtil;
import com.hanghae99chapter5homework.domain.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/account")
@RestController
public class AccountController {

    private final AccountService accountService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public GlobalResDto signup(@RequestBody @Valid AccountRequestDto accountRequestDto){
        return accountService.signup(accountRequestDto);
    }

    @PostMapping("/login")
    public GlobalResDto login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse){
        return accountService.login(loginRequestDto, httpServletResponse);
    }

    @GetMapping("/renew")
    public GlobalResDto issuedToken(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse httpServletResponse){
        httpServletResponse.addHeader(JwtUtil.ACCESS_TOKEN, jwtUtil.createToken(userDetails.getAccount().getEmail(), "Access"));
        return new GlobalResDto("Success IssuedToken", HttpStatus.OK.value());
    }

    @PostMapping("/logout")
    public GlobalResDto logout(HttpServletRequest httpServletRequest){
        return accountService.logout(httpServletRequest);
    }

}
