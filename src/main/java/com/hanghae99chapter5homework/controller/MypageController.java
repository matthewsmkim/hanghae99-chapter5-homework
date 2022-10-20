package com.hanghae99chapter5homework.controller;


import com.hanghae99chapter5homework.domain.Account;
import com.hanghae99chapter5homework.domain.Comment;
import com.hanghae99chapter5homework.domain.UserDetailsImpl;
import com.hanghae99chapter5homework.domain.request.AccountRequestDto;
import com.hanghae99chapter5homework.entity.Board;

import com.hanghae99chapter5homework.jwt.JwtUtil;
import com.hanghae99chapter5homework.service.AccountService;
import com.hanghae99chapter5homework.service.BoardService;
import com.hanghae99chapter5homework.service.CommentService;

import com.hanghae99chapter5homework.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/mypage")
@RestController
public class MypageController {
    private final MypageService mypageService;
    private final AccountService accountService;
    private final CommentService commentService;
    private final JwtUtil jwtUtil;
    private final BoardService boardService;


    @PostMapping("/me")
    public String userEdit(@RequestBody AccountRequestDto form, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return mypageService.userEdit(form, userDetails);
    }

}
