package com.hanghae99chapter5homework.service;

import com.hanghae99chapter5homework.domain.request.LoginRequestDto;
import com.hanghae99chapter5homework.domain.request.AccountRequestDto;
import com.hanghae99chapter5homework.domain.Account;
import com.hanghae99chapter5homework.domain.RefreshToken;
import com.hanghae99chapter5homework.repository.AccountRepository;
import com.hanghae99chapter5homework.repository.RefreshTokenRepository;
import com.hanghae99chapter5homework.global.GlobalResDto;
import com.hanghae99chapter5homework.jwt.TokenDto;
import com.hanghae99chapter5homework.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public GlobalResDto signup(AccountRequestDto accountRequestDto) {

        //email 중복 검사
        if(accountRepository.findByEmail(accountRequestDto.getEmail()).isPresent()){
            throw new RuntimeException("Overlap Check");
        }

        if(!accountRequestDto.getPassword().equals(accountRequestDto.getCheckPassword())){
            throw new RuntimeException("Password does not match");
        }

        Account account = Account.builder()
                .email(accountRequestDto.getEmail())
                .nickname(accountRequestDto.getNickname())
                .password(passwordEncoder.encode(accountRequestDto.getPassword()))
                .build();
        accountRepository.save(account);
        return new GlobalResDto("Success Signup", HttpStatus.OK.value());
    }

    @Transactional
    public GlobalResDto login(LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse) {

        Account account = accountRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(
                () -> new RuntimeException("Not found account")
        );

        if(!passwordEncoder.matches(loginRequestDto.getPassword(), account.getPassword())){
            throw new RuntimeException("Password does not match");
        }

        TokenDto tokenDto = jwtUtil.createAllToken(loginRequestDto.getEmail());

        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByEmail(loginRequestDto.getEmail());

        if (refreshToken.isPresent()){
            refreshTokenRepository.save(refreshToken.get().update(tokenDto.getRefreshToken()));
        }else {
            RefreshToken renewRefreshToken = new RefreshToken(tokenDto.getRefreshToken(), loginRequestDto.getEmail());
            refreshTokenRepository.save(renewRefreshToken);
        }

        setHeader(httpServletResponse, tokenDto);
        return new GlobalResDto("Login Success", HttpStatus.OK.value());

    }

    public GlobalResDto logout(HttpServletRequest httpServletRequest) {
        if(!jwtUtil.refreshTokenValidation(httpServletRequest.getHeader("Refresh-Token"))){
            throw new RuntimeException("Token has expired");
        }
        Account account = jwtUtil.getAccountFromAuthentication();
        if (null == account){
            throw new RuntimeException("Member Not Found");
        }

        return jwtUtil.deleteRefreshToken(account);
    }

    public GlobalResDto mypage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return jwtUtil.;
    }

    private static void setHeader(HttpServletResponse httpServletResponse, TokenDto tokenDto) {
        httpServletResponse.addHeader(JwtUtil.ACCESS_TOKEN, tokenDto.getAccessToken());
        httpServletResponse.addHeader(JwtUtil.REFRESH_TOKEN, tokenDto.getRefreshToken());
    }

}
