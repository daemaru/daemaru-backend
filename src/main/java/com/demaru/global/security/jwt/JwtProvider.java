package com.demaru.global.security.jwt;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;

    public String createAccessToken(UUID userId) {
        return createToken(userId, "Access", 60 * 60L); // 1시간 (3600초)
    }

    private String createToken(UUID userId, String type, Long expireTimeSec) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, secret)
                .setHeaderParam(Header.JWT_TYPE, type)
                .setId(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireTimeSec * 1000))
                .compact();
    }
}
