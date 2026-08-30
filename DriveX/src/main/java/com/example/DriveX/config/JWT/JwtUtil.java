package com.example.DriveX.config.JWT;

import java.security.Key;
import java.util.Date;

import com.example.DriveX.Enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

  @Value("${jwt.secret}")
  private String secret;

  @Value("${jwt.expiration-ms}")
  private Long expirationMs;


  private Key getSigningKey()
  {

         return Keys.hmacShaKeyFor(secret.getBytes());

  }


  public String generateToken(String email ,Long userId ,  Role role)
  {

      return Jwts.builder()
              .setSubject(email)
              .claim("userId" , userId)
              .claim("role", role)
              .setIssuedAt(new Date())
              .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
              .signWith(getSigningKey(), SignatureAlgorithm.HS256)
              .compact();


  }

  public String extractEmail(String token)
  {

       return ParseClaims(token).getSubject();

  }

    public Long extractId(String token) {
        return ParseClaims(token).get("id", Long.class);
    }


  public boolean isTokenValid(String token)
  {

         try{

             ParseClaims(token);

             return  true;

         }
         catch(Exception e)
         {

             System.out.println(e.getMessage());

             return false;

         }

  }


  private Claims ParseClaims(String token)
  {

      return Jwts.parserBuilder()
              .setSigningKey(getSigningKey())
              .build()
              .parseClaimsJws(token)
              .getBody();

  }




}
