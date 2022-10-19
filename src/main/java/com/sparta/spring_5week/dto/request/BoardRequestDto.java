package com.sparta.spring_5week.dto.request;

import com.sparta.spring_5week.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class BoardRequestDto {

    private String title;
    private String comment;

    public BoardRequestDto(Board board){
        this.title = board.getTitle();
        this.comment = board.getComment();
    }

}
