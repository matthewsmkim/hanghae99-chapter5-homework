package com.sparta.spring_5week.dto.response;

import com.sparta.spring_5week.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardResponseDto {

    private String title;
    private String comment;

    public BoardResponseDto(Board board){
        this.title = board.getTitle();
        this.comment = board.getComment();
    }
}
