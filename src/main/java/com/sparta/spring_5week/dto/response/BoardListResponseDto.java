package com.sparta.spring_5week.dto.response;

import com.sparta.spring_5week.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class BoardListResponseDto {

    public String title;
    public String comment;
    public LocalDateTime createdAt;

    public BoardListResponseDto(Board board){
        this.title = board.getTitle();
        this.comment = board.getComment();
        this.createdAt = board.getCreatedAt();
    }
}
