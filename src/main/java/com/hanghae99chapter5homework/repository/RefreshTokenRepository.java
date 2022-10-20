package com.hanghae99chapter5homework.repository;

import com.hanghae99chapter5homework.domain.Member;
import com.hanghae99chapter5homework.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByEmail(String email);
    Optional<RefreshToken> findByAccount(Member member);
}
