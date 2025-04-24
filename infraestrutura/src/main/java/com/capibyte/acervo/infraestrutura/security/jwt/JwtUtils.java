package com.capibyte.acervo.infraestrutura.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${seguranca.jwtsecret}")
    private String jwtSecret;
    private final int jwtExpirationMs = 900000;

    public String generateTokenFromUserDetails(UserDetails userDetails) {

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigninKey())
                .compact();
    }

    public Key getSigninKey() {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        return key;
    }

    public String getUsernameToken(String token) {
        return Jwts.parser()
                .setSigningKey(getSigninKey()) // Atualizado para parserBuilder()
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken (String token) throws MalformedJwtException,  ExpiredJwtException, UnsupportedJwtException, IllegalArgumentException {
        try{
            Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            System.out.println("Token invalido "+e.getMessage());
        }catch(ExpiredJwtException e) {
            System.out.println("Token expirado " + e.getMessage());
        }catch(UnsupportedJwtException e) {
            System.out.println("Token não suportado " + e.getMessage());
        }catch(IllegalArgumentException e) {
            System.out.println("Token Argumento inválido " + e.getMessage());
        }
        return false;
    }
}
