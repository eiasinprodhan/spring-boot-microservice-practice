package com.eiasinprodhan.auth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    public static final String SECRET = "FDGSDFGSTR4ET654GSD5G4DFS65G4DFSG54R65ET4EWR65T4DGD";


    public String generateJwtToken(String username, String password) {
        return  Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .signWith(getKey())
                .compact();
    }

    private Key getKey() {
        byte[] bytes = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(bytes);
    }

    private Claims getClaimsFromToken(String token) {
        Jwts.parser().verifyWith(Sec)
    }
}
