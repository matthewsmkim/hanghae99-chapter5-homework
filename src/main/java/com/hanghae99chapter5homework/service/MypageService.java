package com.hanghae99chapter5homework.service;

import com.hanghae99chapter5homework.global.GlobalResDto;
import com.hanghae99chapter5homework.jwt.JwtUtil;
import com.hanghae99chapter5homework.repository.MypageRepository;
import com.hanghae99chapter5homework.repository.RefreshTokenRepository;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MypageService {

    private final MypageRepository boardRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    public MypageService(MypageRepository boardRepository, RefreshTokenRepository refreshTokenRepository, JwtUtil jwtUtil) {
        this.boardRepository = boardRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtUtil = jwtUtil;
    }

    public GlobalResDto getBoard(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

       return new GlobalResDto("Success Signup", HttpStatus.OK.value());
    }
}
