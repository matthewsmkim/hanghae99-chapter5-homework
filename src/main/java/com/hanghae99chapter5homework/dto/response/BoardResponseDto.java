package com.hanghae99chapter5homework.dto.response;

import com.hanghae99chapter5homework.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardResponseDto {

    private String title;
    private String desc;

    public BoardResponseDto(Board board){
        this.title = board.getTitle();
        this.desc = board.getDesc();
    }
}
