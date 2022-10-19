package com.sparta.spring_5week.controller;

import com.sparta.spring_5week.dto.request.BoardRequestDto;
import com.sparta.spring_5week.dto.response.BoardListResponseDto;
import com.sparta.spring_5week.dto.response.BoardResponseDto;
import com.sparta.spring_5week.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    // 게시판 전체 조회
    @GetMapping("/list")
    public List<BoardListResponseDto> boardList(){
        return boardService.boardList();
    }

    // 특정 게시판 조회
    @GetMapping("/one/{id}")
    public BoardRequestDto boardOneSelect(@PathVariable Long id){
        return boardService.boardOne(id);
    }

    // 게시판 생성
    @PostMapping("/add")
    public BoardResponseDto boardCreate(@RequestBody BoardRequestDto requestDto) {
        return boardService.create(requestDto);
    }

    // 게시판 수정
    @PutMapping("/update/{id}")
    public Long boardUpdate(@PathVariable Long id, @RequestBody BoardRequestDto requestDto){
        return boardService.update(id, requestDto);
    }

    // 게시판 삭제
    @DeleteMapping("delete/{id}")
    public BoardResponseDto boardDelete(@PathVariable Long id){
        return boardService.delete(id);
    }
}
