package com.sparta.spring_5week.dto.response;

import com.sparta.spring_5week.entity.Reple;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class RepleResponseDto {

    private Long board_Id;
    private String comment;
    private LocalDateTime createdAt;

    public RepleResponseDto(Reple reple){
        this.comment = reple.getComment();
        this.createdAt = reple.getCreatedAt();
        this.board_Id = reple.getId();
    }
}
