package com.example.rtu.auth;


import com.example.rtu.repository.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private static  final String SecretKey = "5368566D5970337336763979244226452948404D635166546A576E5A72347437";

    private final UserRepo userRepo;





    public JWTService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }







    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+10000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public  Boolean isTokenValid(String jwt, UserDetails userDetails){
        final String userName = extractUserName(jwt);
        return ((userName.equals(userDetails.getUsername())) && !isTokenExpired(jwt));
    }

    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }

    private Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }


    public String extractUserName(String jwt) {

        return extractClaim(jwt, Claims::getSubject);
    }

    public<T> T extractClaim(String jwt, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);

    }
    private Claims extractAllClaims(String jwt){


        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();

    }

    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }


}
