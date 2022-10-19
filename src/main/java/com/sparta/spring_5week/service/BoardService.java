package com.sparta.spring_5week.service;

import com.sparta.spring_5week.dto.request.BoardRequestDto;
import com.sparta.spring_5week.dto.response.BoardListResponseDto;
import com.sparta.spring_5week.dto.response.BoardResponseDto;
import com.sparta.spring_5week.entity.Board;
import com.sparta.spring_5week.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    //게시판 전체 조회
    public List<BoardListResponseDto> boardList(){
        //List<Board> boardList = boardRepository.findAll();
        List<Board> boardList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        return boardList.stream().map(BoardListResponseDto::new).collect(Collectors.toList());
    }

    // 특정 게시글 조회
    public BoardRequestDto boardOne(Long id){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디는 존재하지 않습니다.")
        );
        return new BoardRequestDto(board);
    }

    // 게시판 생성
    public BoardResponseDto create(BoardRequestDto requestDto){
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return new BoardResponseDto(board);
    }

    // 게시판 수정
    @Transactional
    public Long update(Long id, BoardRequestDto requestDto){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이다가 존재하지 않습니다.")
        );
        board.update(requestDto);
        return board.getId();
    }

    // 게시판 삭제
    public BoardResponseDto delete(Long id){
        boardRepository.deleteById(id);
        return new BoardResponseDto();
    }

//    @Transactional
//    public Board isPresentPost(Long id){
//        Optional<Board> optionalBoard = boardRepository.findById(id);
//        return optionalBoard.orElse(null);
//    }
}
