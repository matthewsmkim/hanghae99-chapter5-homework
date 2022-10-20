package com.hanghae99chapter5homework.service;

import com.hanghae99chapter5homework.domain.Member;
import com.hanghae99chapter5homework.entity.Board;
import org.springframework.data.domain.Page;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;
public interface BoardService {
    public Long board(Long memberId, Long categoryId, String title, String contents);
    public Long updateBoard(Long boardId, Long categoryId, String title, String contents);
    public Board findOne(Long boardId);
    public List<Board> findAll();
    public List<Board> findByMember(Member member);
    public void deleteBoard(Long boardId);

    Page<Board> getPostListByMember(Member member, Pageable pageable);
}