package com.sparta.spring_5week.service;

import com.sparta.spring_5week.dto.request.RepleRequestDto;
import com.sparta.spring_5week.dto.response.RepleResponseDto;
import com.sparta.spring_5week.entity.Board;
import com.sparta.spring_5week.entity.Reple;
import com.sparta.spring_5week.repository.BoardRepository;
import com.sparta.spring_5week.repository.RepleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class RepleService {

    private final RepleRepository repleRepository;
    private final BoardService boardService;
    private final BoardRepository boardRepository;

//    public RepleResponseDto create(RepleRequestDto requestDto){
//        Reple reple = new Reple(requestDto);
//        repleRepository.save(reple);
//        return new RepleResponseDto(reple);
//    }

    public RepleResponseDto create(Long board_id, RepleRequestDto requestDto){

        Board board = boardRepository.findById(board_id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디는 존재하지 않습니다.")
        );

//        Optional<Board> board = Optional.ofNullable(boardRepository.findById(board_id).orElseThrow(
//                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
//        ));
        Reple reple = new Reple(board, requestDto);
        repleRepository.save(reple);
        return new RepleResponseDto(reple);
    }


    public Long update(Long id, RepleRequestDto requestDto){
        Reple reple = repleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        reple.update(requestDto);
        return reple.getId();
    }

    public RepleResponseDto delete(Long id){
        repleRepository.deleteById(id);
        return new RepleResponseDto();
    }
}
