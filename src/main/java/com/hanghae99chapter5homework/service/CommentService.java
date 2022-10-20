package com.hanghae99chapter5homework.service;

import com.hanghae99chapter5homework.domain.Comment;
import com.hanghae99chapter5homework.domain.request.CommentRequestDto;
import com.hanghae99chapter5homework.dto.response.CommentListResponseDto;
import com.hanghae99chapter5homework.dto.response.CommentResponseDto;
import com.hanghae99chapter5homework.entity.Board;
import com.hanghae99chapter5homework.jwt.JwtUtil;
import com.hanghae99chapter5homework.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardService boardService;
    private final JwtUtil jwtUtil;

    public CommentResponseDto create(CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest) {
        jwtUtil.validateTokenAndMember(httpServletRequest);

        Board board = boardService.isPresentBoard(commentRequestDto.getPostId());
        if (null == board) {
            throw new RuntimeException("존재하지 않는 게시글입니다.");
        }

        Comment comment = Comment.builder()
                .comment(commentRequestDto.getComment())
                .account(jwtUtil.validateMember(httpServletRequest))
                .board(board)
                .build();

        commentRepository.save(comment);
        return new CommentResponseDto(comment);

    }

    public List<CommentListResponseDto> getAllCommentsByPost(Long id) {
        List<Comment> commentList = commentRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        return commentList.stream().map(CommentListResponseDto::new).collect(Collectors.toList());
    }

    public Long update(Long id, CommentRequestDto commentRequestDto, HttpServletRequest httpServletRequest) {
        jwtUtil.validateTokenAndMember(httpServletRequest);

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("존재하지 않는 댓글입니다.")
        );

        Board board = boardService.isPresentBoard(commentRequestDto.getPostId());
        if (null == board) {
            throw new RuntimeException("존재하지 않는 게시글입니다.");
        }

        comment.update(commentRequestDto);
        return comment.getId();


    }

    public Long delete(Long id, HttpServletRequest httpServletRequest) {
        jwtUtil.validateTokenAndMember(httpServletRequest);

        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("존재하지 않는 댓글입니다.")
        );

        commentRepository.deleteById(id);
        return id;

    }
}
