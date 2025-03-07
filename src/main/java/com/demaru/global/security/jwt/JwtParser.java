package com.demaru.global.security.jwt;

import com.demaru.global.security.principle.AdminDetails;
import com.demaru.global.security.principle.AdminDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtParser {
    @Value("${jwt.secret}")
    private String secret;

    private final AdminDetailsService adminDetailsService;

    public Authentication getAuthentication(String token) {
        Jws<Claims> claims = getClaims(token);

        if (!claims.getHeader().get(Header.JWT_TYPE).equals("Access")) throw new RuntimeException("Invalid token");

        UserDetails userDetails = getDetails(claims.getBody());

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Jws<Claims> getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
        }catch (Exception e) {
            if(e instanceof InvalidClaimException) throw e;
            if(e instanceof ExpiredJwtException) throw e;
            if(e instanceof JwtException) throw e;

            throw new RuntimeException("Internal");
        }
    }

    private UserDetails getDetails(Claims body) {
        return adminDetailsService.loadUserByUsername(body.getId());
    }
}
