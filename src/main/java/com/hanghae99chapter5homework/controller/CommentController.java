package com.hanghae99chapter5homework.controller;

import com.hanghae99chapter5homework.domain.request.CommentRequestDto;
import com.hanghae99chapter5homework.dto.response.CommentListResponseDto;
import com.hanghae99chapter5homework.dto.response.CommentResponseDto;
import com.hanghae99chapter5homework.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/add")
    public CommentResponseDto commentCreate(@RequestBody CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest){
        return commentService.create(commentRequestDto, httpServletRequest);
    }

    //모든 댓글
    @GetMapping("/list")
    public List<CommentListResponseDto> getAllComments(@PathVariable Long id){
        return commentService.getAllCommentsByPost(id);
    }

    //댓글 업데이트
    @PutMapping("/auth/{id}")
    public Long CommentUpdate(@PathVariable Long id, @RequestBody CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest){
        return commentService.update(id, commentRequestDto, httpServletRequest);
    }

    //댓글 삭제
    @DeleteMapping ("/auth/{id}")
    public Long delete(@PathVariable Long id, HttpServletRequest httpServletRequest){
        return commentService.delete(id, httpServletRequest);
    }


}
