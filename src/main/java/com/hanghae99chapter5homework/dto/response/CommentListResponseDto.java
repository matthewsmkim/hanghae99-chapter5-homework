package com.hanghae99chapter5homework.dto.response;

import com.hanghae99chapter5homework.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentListResponseDto {

    public String comment;

    public CommentListResponseDto(Comment comment) {
        this.comment = comment.getComment();
    }
}
