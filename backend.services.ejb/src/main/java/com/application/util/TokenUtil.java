package com.application.util;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class TokenUtil {

    private String secrectKeyJsonWebToken = "9aaf7b6e2c692f9e3a36c9d67ec7c609d42c50b93cc14b79f8ff83536c46f31c";

    private static final int TIEMPO_TOKEN = 12 * 60 * 60 * 1000;

    public String extraerNombreUsuarioJwt(String token) {
        return this.extraerClaims(token, Claims::getSubject);
    }

    private Key getFirmaClave() {
        byte[] keyBytes = Decoders.BASE64.decode(secrectKeyJsonWebToken);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extraerAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getFirmaClave())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extraerClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.extraerAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String setGenerarToken(Map<String, Object> claims, String username) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TIEMPO_TOKEN))
                .signWith(this.getFirmaClave(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValido(String token, String username) {
        final String usuario = this.extraerNombreUsuarioJwt(token);
        return (usuario.equals(username)) && !this.isTokenExpirado(token);
    }

    public boolean isTokenExpirado(String token) {
        try {
            return this.extraerExpiracionToken(token).before(new Date());
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public Date extraerExpiracionToken(String token) {
        return this.extraerClaims(token, Claims::getExpiration);
    }

}
