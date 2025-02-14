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
    @Value("jwt.secret")
    private String secret;

    public String createAccessToken(UUID userId) {
        return createToken(userId, "Access", 24 * 60L); // 1시간 // 테스트 환경에선 1일로
    }

    public String createRefreshToken(UUID userId) {
        return createToken(userId, "Refresh",  14 * 24 * 60L); // 14일
    }

    public String createToken(UUID userId, String type, Long expireTimeMs) {
        try {
            return Jwts.builder()
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .setHeaderParam(Header.JWT_TYPE, type)
                    .setId(userId.toString())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + expireTimeMs * 1000))
                    .compact();
        } catch (JwtException e) {
            throw new RuntimeException();
        }
    }
}
