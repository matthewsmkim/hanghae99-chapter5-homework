package com.hanghae99chapter5homework.service;

import com.hanghae99chapter5homework.domain.Member;
import com.hanghae99chapter5homework.entity.Comment;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public class CommentService {
    public Page<Comment> findListByMember(Member member, Pageable pageable) {

    }
}
