package com.hanghae99chapter5homework.service;

import com.hanghae99chapter5homework.domain.UserDetailsImpl;
import com.hanghae99chapter5homework.global.GlobalResDto;
import com.hanghae99chapter5homework.jwt.JwtUtil;
import com.hanghae99chapter5homework.repository.MypageRepository;
import com.hanghae99chapter5homework.repository.RefreshTokenRepository;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class MypageService {

    private final MypageRepository mypageRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    public MypageService(MypageRepository mypageRepository, RefreshTokenRepository refreshTokenRepository, JwtUtil jwtUtil) {
        this.mypageRepository = mypageRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtUtil = jwtUtil;
    }





}
