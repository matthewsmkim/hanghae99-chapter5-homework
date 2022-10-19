package com.sparta.spring_5week.entity;

import com.sparta.spring_5week.dto.request.BoardRequestDto;
import com.sparta.spring_5week.util.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String comment;

//    @OneToMany(mappedBy = "board")
//    private final List<Reple> repleList = new ArrayList<>();


    public Board(BoardRequestDto requestDto){
        this.title = requestDto.getTitle();;
        this.comment = requestDto.getComment();
    }

    public void update(BoardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.comment = requestDto.getComment();
    }

//    public void addReple(Reple reple){
//        reple.setBoard(this);
//        this.repleList.add(reple);
//    }

}
