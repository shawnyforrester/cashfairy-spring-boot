package com.bnymellon.cashfairy.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {

    private static final String SECRET_KEY = "BF79B93BEE87E17146579E3C2FBC8";//this may be placed in the
    //application properties or replaced with a generator service
    public JwtService() {

    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails );
    }
    /**
     * This method generates tokens by setting the claims in the body of the token
     * @param extraClaims
     * @param userDetails
     * @return
     */
    public String generateToken(Map<String, Object> extraClaims,
                                UserDetails userDetails){
        {
            return Jwts.builder()
                    .setClaims(extraClaims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date (System.currentTimeMillis() + 1000 * 60 * 24))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();


        }

    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public String extractUserName(String token) {
        return extractClaim(token, Claims:: getSubject);
    }

    public <T> T extractClaim (String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractALlClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractALlClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
