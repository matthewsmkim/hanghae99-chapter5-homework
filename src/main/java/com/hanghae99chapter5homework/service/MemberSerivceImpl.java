package com.hanghae99chapter5homework.service;

import com.hanghae99chapter5homework.domain.Member;
import com.hanghae99chapter5homework.domain.UserDetailsImpl;
import com.hanghae99chapter5homework.jwt.JwtUtil;
import com.hanghae99chapter5homework.repository.MemberRepository;
import com.hanghae99chapter5homework.repository.RefreshTokenRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class MemberSerivceImpl extends MemberService {

    public MemberSerivceImpl(MemberRepository memberRepository, RefreshTokenRepository refreshTokenRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        super(memberRepository, refreshTokenRepository, jwtUtil, passwordEncoder);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Member member = memberRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("Not Found Member")
        );

        UserDetailsImpl userDetails = new UserDetailsImpl();
        userDetails.setMember(member);
        return userDetails;
    }



    @Transactional
    @Override
    public Long updateInfo(String username, String newName, String email) {
        Member member  = memberRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(username));

        member.setUsername(newName);
        member.setEmail(email);
        return member.getId();
    }
}
