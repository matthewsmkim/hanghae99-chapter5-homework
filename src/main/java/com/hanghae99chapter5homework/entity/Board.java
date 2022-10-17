package com.hanghae99chapter5homework.entity;

import com.hanghae99chapter5homework.dto.request.BoardRequestDto;
import com.hanghae99chapter5homework.util.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String comment;


    public Board(BoardRequestDto requestDto){
        this.title = requestDto.getTitle();;
        this.comment = requestDto.getComment();
    }

    public void update(BoardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.comment = requestDto.getComment();
    }

}
