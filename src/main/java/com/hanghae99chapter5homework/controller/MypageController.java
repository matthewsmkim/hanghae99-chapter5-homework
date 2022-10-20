package com.hanghae99chapter5homework.controller;

import com.hanghae99chapter5homework.global.GlobalResDto;
import com.hanghae99chapter5homework.jwt.JwtUtil;
import com.hanghae99chapter5homework.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RequestMapping("/api/mypage")
@RestController
public class MypageController {
    private final MypageService mypageService;
    private final JwtUtil jwtUtil;




    @GetMapping("/")
    public GlobalResDto getBoard(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return mypageService.getBoard(httpServletRequest, httpServletResponse);
    }
}
