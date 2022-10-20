package com.hanghae99chapter5homework.jwt;

import com.hanghae99chapter5homework.domain.Member;
import com.hanghae99chapter5homework.domain.RefreshToken;
import com.hanghae99chapter5homework.domain.UserDetailsImpl;
import com.hanghae99chapter5homework.global.GlobalResDto;
import com.hanghae99chapter5homework.repository.RefreshTokenRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserDetailsService userDetailsService;

    //토큰 필드 생성

    public static final String ACCESS_TOKEN = "Access-Token";
    public static final String REFRESH_TOKEN = "Refresh-Token";
    private static final long ACCESS_TIME = 10 * 1000L;
    private static final long REFRESH_TIME = 10000 * 1000L;


    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init(){
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    //header 토큰을 가져오는 기능

    public String getHeaderToken(HttpServletRequest request, String type){
        return type.equals("Access") ? request.getHeader(ACCESS_TOKEN) : request.getHeader(REFRESH_TOKEN);
    }

    //토큰 생성

    public TokenDto createAllToken(String email){
        return new TokenDto(createToken(email,"Access"), createToken(email,"Refresh"));
    }

    public String createToken(String email, String type){

        Date date = new Date();
        long time = type.equals("Access") ? ACCESS_TIME : REFRESH_TIME;

        return Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(date.getTime() + time))
                .setIssuedAt(date)
                .signWith(key,signatureAlgorithm)
                .compact();

    }

    //토큰 검증
    public Boolean tokenValidation(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (Exception e){
            log.error(e.getMessage());
            return false;
        }
    }

    //RT 검증

    public Boolean refreshTokenValidation(String token){

        //1차 검증
        if(!tokenValidation(token))return false;

        //DB와 비교
        Optional<RefreshToken> refreshToken = refreshTokenRepository.findByEmail(getEmailFromToken(token));

        return refreshToken.isPresent() && token.equals(refreshToken.get().getRefreshToken());



    }

    //인증 객체 생성
    public Authentication createAuthentication(String email){
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }


    //토큰에서 email 가져오기
    public String getEmailFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public Member getAccountFromAuthentication(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())){
            return null;
        }
        return ((UserDetailsImpl) authentication.getPrincipal()).getMember();
    }

    @Transactional(readOnly = true)
    public RefreshToken isPresentRefreshToken(Member member){
        Optional<RefreshToken> optionalRefreshToken = refreshTokenRepository.findByAccount(member);
        return optionalRefreshToken.orElse(null);
    }

    @Transactional
    public GlobalResDto deleteRefreshToken(Member account){
        RefreshToken refreshToken = isPresentRefreshToken(account);
        if (null == refreshToken){
            throw new RuntimeException("Token Not Found");
        }

        refreshTokenRepository.delete(refreshToken);
        return new GlobalResDto("Logout", HttpStatus.OK.value());
    }



}
