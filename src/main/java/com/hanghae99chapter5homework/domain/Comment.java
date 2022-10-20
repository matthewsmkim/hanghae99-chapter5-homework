package com.hanghae99chapter5homework.domain;

import com.hanghae99chapter5homework.domain.request.CommentRequestDto;
import com.hanghae99chapter5homework.entity.Board;
import com.hanghae99chapter5homework.global.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String comment;

    @JoinColumn(name = "account_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @JoinColumn(name = "board_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    public void update(CommentRequestDto commentRequestDto){
        this.comment = commentRequestDto.getComment();
    }

    public boolean validateAccount(Account account){
        return !this.account.equals(account);
    }
}
