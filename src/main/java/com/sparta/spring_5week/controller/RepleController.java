package com.sparta.spring_5week.controller;

import com.sparta.spring_5week.dto.request.RepleRequestDto;
import com.sparta.spring_5week.dto.response.RepleResponseDto;
import com.sparta.spring_5week.entity.Board;
import com.sparta.spring_5week.entity.Reple;
import com.sparta.spring_5week.repository.BoardRepository;
import com.sparta.spring_5week.repository.RepleRepository;
import com.sparta.spring_5week.service.RepleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reple")
public class RepleController {

    private final RepleService repleService;
    private final BoardRepository boardRepository;
    private final RepleRepository repleRepository;

    // 댓글 생성
//    @PostMapping("/add")
//    public Reple repleCreate(@RequestParam Long boardId, @RequestBody RepleRequestDto requestDto){
//        Reple reple = new Reple(requestDto);
//        Optional<Board> optionalBoard  = boardRepository.findById(boardId);
//        optionalBoard.ifPresent(board -> board.addReple(reple));
//        return repleRepository.save(reple);
//    }

    @PostMapping("/add")
    public RepleResponseDto repleCreate(@RequestParam Long boardId, @RequestBody RepleRequestDto requestDto){
        return repleService.create(boardId, requestDto);
    }

    // 댓글 수정
    @PutMapping("/update/{id}")
    public Long repleUpdate(@PathVariable Long id, @RequestBody RepleRequestDto requestDto){
        return repleService.update(id, requestDto);
    }

    //댓글 삭제
    @DeleteMapping("delete/{id}")
    public RepleResponseDto repleDelete(@PathVariable Long id){
        return repleService.delete(id);
    }
}
