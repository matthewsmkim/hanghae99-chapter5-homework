package com.hanghae99chapter5homework.controller;

import com.hanghae99chapter5homework.domain.request.LoginRequestDto;
import com.hanghae99chapter5homework.domain.request.AccountRequestDto;
import com.hanghae99chapter5homework.service.MemberService;
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
public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public GlobalResDto signup(@RequestBody @Valid AccountRequestDto accountRequestDto){
        return memberService.signup(accountRequestDto);
    }

    @PostMapping("/login")
    public GlobalResDto login(@RequestBody @Valid LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse){
        return memberService.login(loginRequestDto, httpServletResponse);
    }

    @GetMapping("/renew")
    public GlobalResDto issuedToken(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse httpServletResponse){
        httpServletResponse.addHeader(JwtUtil.ACCESS_TOKEN, jwtUtil.createToken(userDetails.getMember().getEmail(), "Access"));
        return new GlobalResDto("Success IssuedToken", HttpStatus.OK.value());
    }

    @PostMapping("/logout")
    public GlobalResDto logout(HttpServletRequest httpServletRequest){
        return memberService.logout(httpServletRequest);
    }

//    @GetMapping("/mypage")
//    public GlobalResDto mypage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//        return accountService.mypage(httpServletRequest, httpServletResponse);
//    }

}
