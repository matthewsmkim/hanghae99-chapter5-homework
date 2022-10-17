package com.hanghae99chapter5homework.controller;

import com.hanghae99chapter5homework.dto.request.CommentRequestDto;
import com.hanghae99chapter5homework.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/add")
    public CommentResponseDto commentCreate(CommentRequestDto requestDto){
        return commentService.create(requestDto);
    }


}
