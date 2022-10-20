package com.hanghae99chapter5homework.repository;

import com.hanghae99chapter5homework.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
