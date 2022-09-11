package com.guwor.reimbursementapi.services;

import com.guwor.reimbursementapi.dtos.responses.Principal;
import com.guwor.reimbursementapi.utils.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import java.util.Date;

public class TokenService {

    private JwtConfig jwtConfig;

    public TokenService(){ super();}

    public TokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(Principal subject){
        long now = System.currentTimeMillis();
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId((subject.getUser_id()))
                .setIssuer("reimbursementapi")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration()))
                .setSubject(subject.getUsername())
                .claim("role", subject.getRole_id())
                .signWith(jwtConfig.getSignatureAlgorithm(), jwtConfig.getSigningKey());

        return jwtBuilder.compact();
    }
    public Principal extractRequestDetails(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();

            return new Principal(claims.getId(), claims.getSubject(), claims.get("role", String.class));
        }catch (Exception e){
            return null;
        }
    }
}
