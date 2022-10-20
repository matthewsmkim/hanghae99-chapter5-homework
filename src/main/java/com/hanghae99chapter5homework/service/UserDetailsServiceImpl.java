//package com.hanghae99chapter5homework.service;
//
//import com.hanghae99chapter5homework.domain.Member;
//import com.hanghae99chapter5homework.repository.MemberRepository;
//import com.hanghae99chapter5homework.domain.UserDetailsImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final MemberRepository memberRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//
//
//        Member member = memberRepository.findByEmail(email).orElseThrow(
//                () -> new RuntimeException("Not Found Member")
//        );
//
//        UserDetailsImpl userDetails = new UserDetailsImpl();
//        userDetails.setAccount(member);
//        return userDetails;
//    }
//
//    @Transactional
//    @Override
//    public Long updateInfo(String username, String newName, String email) {
//        Member member  = memberRepository.findByUsername(username)
//                .orElseThrow(()-> new UsernameNotFoundException(username));
//
//        member.setUsername(newName);
//        member.setEmail(email);
//        return member.getId();
//    }
//}
