package com.hanghae99chapter5homework.service;

import com.hanghae99chapter5homework.domain.Account;
import com.hanghae99chapter5homework.domain.UserDetailsImpl;
import com.hanghae99chapter5homework.domain.request.AccountRequestDto;
import com.hanghae99chapter5homework.global.GlobalResDto;
import com.hanghae99chapter5homework.jwt.JwtUtil;
import com.hanghae99chapter5homework.repository.AccountRepository;
import com.hanghae99chapter5homework.repository.MypageRepository;
import com.hanghae99chapter5homework.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MypageService {

    private final MypageRepository boardRepository;

    private final AccountRepository accountRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public GlobalResDto getBoard(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

       return new GlobalResDto("Success Signup", HttpStatus.OK.value());
    }


    @Transactional
    public String userEdit(AccountRequestDto form, UserDetailsImpl userDetails) {
        Account account = accountRepository.findByEmail(form.getEmail()).orElseThrow(
                () -> new RuntimeException("Not found account")
        );

        if(!account.getEmail().equals(userDetails.getAccount().getEmail())){
            throw new RuntimeException("Not matches account");
        }

        if(!passwordEncoder.matches(form.getPassword(), account.getPassword())){
            throw new RuntimeException("Password does not match");
        }

//        account.setNickname(form.getNickname());
//        account.setPassword(form.getPassword());
        account.update(form);

        return "Success";

    }
}
