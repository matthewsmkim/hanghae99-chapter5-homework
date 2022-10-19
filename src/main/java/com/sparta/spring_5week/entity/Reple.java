package com.sparta.spring_5week.entity;


import com.sparta.spring_5week.dto.request.RepleRequestDto;
import com.sparta.spring_5week.util.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@Entity(name = "reple")
@NoArgsConstructor
@Getter
@Setter
public class Reple extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPLE_ID")
    private Long id;

    @Column(nullable = false)
    private String comment;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID", nullable = false)
    private Board board;

    public Reple(Board board, RepleRequestDto requestDto){
        this.comment = requestDto.getComment();
        this.board = board;
    }

    public void update(RepleRequestDto requestDto){
        this.comment = requestDto.getComment();
    }
}
