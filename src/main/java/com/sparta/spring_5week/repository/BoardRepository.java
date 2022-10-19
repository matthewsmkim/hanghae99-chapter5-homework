package com.sparta.spring_5week.repository;

import com.sparta.spring_5week.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
