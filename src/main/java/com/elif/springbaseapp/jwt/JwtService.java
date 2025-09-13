package com.elif.springbaseapp.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

    // Create the token service

    // define the secret key from application.yml
    @Value("${spring.security.jwt.secret-key}")            // import for -> beans.factory.annotation.Value
    private String SECRET_KEY;


    // Create Token
    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())                  // Username
                .setIssuedAt(new Date())                                // Created Date
                .setExpiration(new Date(System.currentTimeMillis() *1000*60*60*24))
                .signWith(getSigninKey(),SignatureAlgorithm.HS256)      // Token key-sign
                .compact();
    }


    // Use to withdraw some details from the token
    public <T> T extractClaims(String token, Function<Claims, T> claimsTFunction){
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token).getBody();
        return claimsTFunction.apply(claims);
    }


    public String getUsernameByToken(String token){
        return extractClaims(token, Claims::getSubject);
    }

    public boolean validateToken(String token){
        String username = getUsernameByToken(token);
        return username != null && isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        Date expiredDate = extractClaims(token, Claims::getExpiration);
        return expiredDate.before(new Date());      // Compare Expired Date and Current Date
    }


    public Key getSigninKey(){
        // Key returns ByteCode
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
