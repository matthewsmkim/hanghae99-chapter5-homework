package com.hanghae99chapter5homework.dto.response;

import com.hanghae99chapter5homework.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class BoardListResponseDto {

    //public Long id;
    public String title;
    public String desc;
    public LocalDateTime createdAt;

    public BoardListResponseDto(Board board){
        //this.id = board.getId();
        this.title = board.getTitle();
        this.desc = board.getDesc();
        this.createdAt = board.getCreatedAt();
    }
}
