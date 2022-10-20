package com.hanghae99chapter5homework.dto.response;

import com.hanghae99chapter5homework.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentResponseDto {

    private String comment;

    public CommentResponseDto (Comment comment){
        this.comment = comment.getComment();
    }

}
