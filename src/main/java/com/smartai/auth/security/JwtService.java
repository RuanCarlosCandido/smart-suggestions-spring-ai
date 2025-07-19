package com.smartai.auth.security;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    // Idealmente, armazene isso em uma variável de ambiente segura ou Vault
    private static final String SECRET_KEY = "sua-chave-ultra-secreta-com-mais-de-32-caracteres";

    private SecretKey getChave() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String gerarToken(UserDetails user) {
        Instant agora = Instant.now();

        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(Date.from(agora))
                .expiration(Date.from(agora.plus(1, ChronoUnit.DAYS)))
                .signWith(getChave())
                .compact();
    }

    public String extrairUsername(String token) {
        return parseToken(token).getPayload().getSubject();
    }

    public boolean tokenValido(String token, UserDetails userDetails) {
        String username = extrairUsername(token);
        return username.equals(userDetails.getUsername()) && !tokenExpirado(token);
    }

    private boolean tokenExpirado(String token) {
        Date expiration = parseToken(token).getPayload().getExpiration();
        return expiration.before(new Date());
    }

    private Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .verifyWith(getChave())
                .build()
                .parseSignedClaims(token);
    }
}
