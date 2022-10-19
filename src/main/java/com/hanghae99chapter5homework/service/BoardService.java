package com.hanghae99chapter5homework.service;

import com.hanghae99chapter5homework.domain.Account;
import com.hanghae99chapter5homework.dto.request.BoardRequestDto;
import com.hanghae99chapter5homework.dto.response.BoardListResponseDto;
import com.hanghae99chapter5homework.dto.response.BoardResponseDto;
import com.hanghae99chapter5homework.entity.Board;
import com.hanghae99chapter5homework.jwt.JwtUtil;
import com.hanghae99chapter5homework.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final JwtUtil jwtUtil;

    //게시판 전체 조회
    @Transactional
    public List<BoardListResponseDto> boardList(){
        //List<Board> boardList = boardRepository.findAll();
        List<Board> boardList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        return boardList.stream().map(BoardListResponseDto::new).collect(Collectors.toList());
    }

    // 특정 게시글 조회
    @Transactional
    public BoardRequestDto boardOne(Long id){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디는 존재하지 않습니다.")
        );
        return new BoardRequestDto(board);
    }

    // 게시판 생성
    @Transactional
    public BoardResponseDto create(BoardRequestDto requestDto, HttpServletRequest httpServletRequest){
        jwtUtil.validateTokenAndMember(httpServletRequest);

        Board board = new Board(requestDto);
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }


    // 게시판 수정
    @Transactional
    public Long update(Long id, BoardRequestDto requestDto, HttpServletRequest httpServletRequest){
        jwtUtil.validateTokenAndMember(httpServletRequest);

        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이다가 존재하지 않습니다.")
        );
        board.update(requestDto);
        return board.getId();
    }

    // 게시판 삭제
    @Transactional
    public BoardResponseDto delete(Long id, HttpServletRequest httpServletRequest){
        jwtUtil.validateTokenAndMember(httpServletRequest);

        boardRepository.deleteById(id);
        return new BoardResponseDto();
    }

}
