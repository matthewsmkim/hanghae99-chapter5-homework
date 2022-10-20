package com.hanghae99chapter5homework.controller;

import com.hanghae99chapter5homework.domain.Member;
import com.hanghae99chapter5homework.dto.MemberForm;
import com.hanghae99chapter5homework.entity.Board;
import com.hanghae99chapter5homework.entity.Comment;
import com.hanghae99chapter5homework.jwt.JwtUtil;
import com.hanghae99chapter5homework.service.BoardService;
import com.hanghae99chapter5homework.service.CommentService;
import com.hanghae99chapter5homework.service.MemberService;
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
    private final MemberService memberService;
    private final CommentService commentService;
    private final JwtUtil jwtUtil;
    private final BoardService boardService;


    @GetMapping("/")
    public String myPageHome(Model model, @AuthenticationPrincipal Member member) {


        MemberForm memberForm = new MemberForm();
        memberForm.setName(member.getUsername());
        memberForm.setEmail(member.getEmail());

        model.addAttribute("accountForm", memberForm);
        return "/";
    }

    @PostMapping("/me")
    public String userEdit(MemberForm form, BindingResult result, @AuthenticationPrincipal Member member) {
        if(result.hasErrors()) {
            return "redirect:/mypage/";
        }

        memberService.updateInfo(member.getUsername(), form.getName(), form.getEmail());
        member.setUsername(form.getName());
        member.setEmail(form.getEmail());

        return "redirect:/mypage/";
    }

    @GetMapping("/contents")
    public String myContents(Model model, @AuthenticationPrincipal Member currentMember,
                             @PageableDefault Pageable pageable) {
        Member member = (Member) memberService.findByUsername(currentMember.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException(currentMember.getUsername()));

        Page<Board> boards = boardService.getPostListByMember(member, pageable);


        model.addAttribute("boards", boards);
        return "/";
    }

    @GetMapping("/comments")
    public String myComments(Model model, @AuthenticationPrincipal Member currentMember,
                             @PageableDefault Pageable pageable) {
        Member member = (Member) memberService.findByUsername(currentMember.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException(currentMember.getUsername()));
        Page<Comment> comments = commentService.findListByMember(member, pageable);


        model.addAttribute("comments", comments);
        return "/";
    }


}
