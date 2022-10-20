package com.hanghae99chapter5homework.repository;

import com.hanghae99chapter5homework.domain.Member;
import com.hanghae99chapter5homework.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    public List<Board> findByMember(Member member);

    Page<Board> findByMember(Member member, Pageable pageable);
}
