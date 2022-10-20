package com.hanghae99chapter5homework.controller;


import com.hanghae99chapter5homework.domain.Account;
import com.hanghae99chapter5homework.domain.Comment;
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


    @GetMapping("/")
    public String myPageHome(Model model, @AuthenticationPrincipal Account account) {


        AccountRequestDto accountRequestDto = new AccountRequestDto();
        AccountRequestDto.setName(account.getUsername());
        AccountRequestDto.setEmail(account.getEmail());

        return "/";
    }

    @PostMapping("/me")
    public String userEdit(AccountRequestDto form, BindingResult result, @AuthenticationPrincipal Account account) {
        if(result.hasErrors()) {
            return "redirect:/mypage/";
        }

        accountService.updateInfo(account.getUsername(), form.getName(), form.getEmail());
        account.setUsername(form.getName());
        account.setEmail(form.getEmail());

        return "redirect:/mypage/";
    }

    @GetMapping("/contents")
    public String myContents(Model model, @AuthenticationPrincipal Account currentAccount,
                             @PageableDefault Pageable pageable) {
        Account account = (Account) accountService.findByUsername(currentAccount.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException(currentAccount.getUsername()));

        Page<Board> boards = boardService.getPostListByMember(account, pageable);


        model.addAttribute("boards", boards);
        return "/";
    }

    @GetMapping("/comments")
    public String myComments(Model model, @AuthenticationPrincipal Account currentAccount,
                             @PageableDefault Pageable pageable) {
        Account account = (Account) accountService.findByUsername(currentAccount.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException(currentAccount.getUsername()));
        Page<Comment> comments = commentService.findListByMember(account, pageable);


        model.addAttribute("comments", comments);
        return "/";
    }


}
