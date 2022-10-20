package com.hanghae99chapter5homework.entity;

import com.hanghae99chapter5homework.domain.Member;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Lob
    private String contents;

    @ColumnDefault("0")
    private int likes;
    @ColumnDefault("0")
    private int dislikes;
    @ColumnDefault("0")
    private int scrap;
    @ColumnDefault("0")
    private int visit;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

    public void createBoard(Member member, String title, String contents) {
    }
}