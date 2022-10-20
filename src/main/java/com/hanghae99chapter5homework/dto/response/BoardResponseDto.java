package com.hanghae99chapter5homework.dto.response;

import com.hanghae99chapter5homework.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardResponseDto {

    //private Long id;
    private String title;
    private String comment;

    public BoardResponseDto(Board board){
        //this.id = board.getId();
        this.title = board.getTitle();
        this.comment = board.getComment();
    }
}
