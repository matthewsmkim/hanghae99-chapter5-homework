package com.hanghae99chapter5homework.dto.request;

import com.hanghae99chapter5homework.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
public class BoardRequestDto {

    private String title;
    private String desc;

    public BoardRequestDto(Board board){
        this.title = board.getTitle();
        this.desc = board.getDesc();
    }

}
