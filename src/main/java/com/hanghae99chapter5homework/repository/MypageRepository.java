package com.hanghae99chapter5homework.repository;

import com.hanghae99chapter5homework.domain.Account;
import com.hanghae99chapter5homework.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MypageRepository extends JpaRepository<Account, Long> {
    Optional<RefreshToken> findByEmail(String email);
    Optional<RefreshToken> findByAccount(Account account);
    Optional<RefreshToken> findAll(Account account);
}
