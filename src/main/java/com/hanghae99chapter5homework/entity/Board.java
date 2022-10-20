package com.hanghae99chapter5homework.entity;

import com.hanghae99chapter5homework.domain.Account;
import com.hanghae99chapter5homework.domain.Comment;
import com.hanghae99chapter5homework.dto.request.BoardRequestDto;
import com.hanghae99chapter5homework.util.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Board extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String comment;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @JoinColumn(name = "account_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;


    public Board(BoardRequestDto requestDto){
        this.title = requestDto.getTitle();;
        this.comment = requestDto.getComment();
    }

    public void update(BoardRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.comment = requestDto.getComment();
    }

    public boolean validateAccount(Account account){
        return !this.account.equals(account);
    }

}
