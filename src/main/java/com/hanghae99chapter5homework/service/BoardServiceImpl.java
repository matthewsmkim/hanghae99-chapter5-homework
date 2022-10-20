package com.hanghae99chapter5homework.service;

import com.hanghae99chapter5homework.domain.Member;
import com.hanghae99chapter5homework.entity.Board;
import com.hanghae99chapter5homework.repository.BoardRepository;
import com.hanghae99chapter5homework.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    @Override
    @Transactional
    public Long board(Long memberId, Long categoryId,
                     String title, String contents) {
        Member member = memberRepository.getOne(memberId);
        Board board = new Board();
        board.createBoard(member, title, contents);
        boardRepository.save(board);

        return board.getId();
    }

    @Override
    @Transactional
    public Long updateBoard(Long boardId, Long categoryId,
                           String title, String contents) {
        Board board = boardRepository.getOne(boardId);
        board.setTitle(title);
        board.setContents(contents);
        board.setUpdateDate(LocalDateTime.now());

        return board.getId();
    }

    @Override
    @Transactional
    public void deleteBoard(Long boardId) {
        Board board = boardRepository.getOne(boardId);
        board.getMember().getboards()
                .removeIf(targetBoard -> targetBoard.equals(board));
        boardRepository.deleteById(boardId);
    }

    @Override
    public Page<Board> getPostListByMember(Member member, Pageable pageable) {
        return null;
    }

    @Override
    public Board findOne(Long boardId) {
        return boardRepository.getOne(boardId);
    }

    @Override
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Override
    public List<Board> findByMember(Member member) {
        return boardRepository.findByMember(member);
    }

}