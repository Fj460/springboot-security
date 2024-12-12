package com.practice.Dockerize.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "ZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKSVV6STFOaUo5LmV5SnBjM01pT2lKUGJteHBibVVnU2xkVUlFSjFhV3hrWlhJaUxDSnBZWFFpT2pFM016UXdNREl5TkRrc0ltVjRjQ0k2TVRjMk5UVXpPREkwT1N3aVlYVmtJam9pZDNkM0xtVjRZVzF3YkdVdVkyOXRJaXdpYzNWaUlqb2lhbkp2WTJ0bGRFQmxlR0Z0Y0d4bExtTnZiU0lzSWtkcGRtVnVUbUZ0WlNJNklrcHZhRzV1ZVNJc0lsTjFjbTVoYldVaU9pSlNiMk5yWlhRaUxDSkZiV0ZwYkNJNkltcHliMk5yWlhSQVpYaGhiWEJzWlM1amIyMGlMQ0pTYjJ4bElqcGJJazFoYm1GblpYSWlMQ0pRY205cVpXTjBJRUZrYldsdWFYTjBjbUYwYjNJaVhYMC5lNFZTTVBjOWxJY214c3QwS1lVSXBIeUN1Qk1VU0ZETnNpd0ZoUW9Oalg0";

    //private static final String SECRET_KEY= "ZXlKMGVYQWlPaUpLVjFRaUxDSmhiR2NpT2lKSVV6STFOaUo5LmV5SnBjM01pT2lKUGJteHBibVVnU2xkVUlFSjFhV3hrWlhJaUxDSnBZWFFpT2pFM016UXdNREl5TkRrc0ltVjRjQ0k2TVRjMk5UVXpPREkwT1N3aVlYVmtJam9pZDNkM0xtVjRZVzF3YkdVdVkyOXRJaXdpYzNWaUlqb2lhbkp2WTJ0bGRFQmxlR0Z0Y0d4bExtTnZiU0lzSWtkcGRtVnVUbUZ0WlNJNklrcHZhRzV1ZVNJc0lsTjFjbTVoYldVaU9pSlNiMk5yWlhRaUxDSkZiV0ZwYkNJNkltcHliMk5yWlhSQVpYaGhiWEJzWlM1amIyMGlMQ0pTYjJ4bElqcGJJazFoYm1GblpYSWlMQ0pRY205cVpXTjBJRUZrYldsdWFYTjBjbUYwYjNJaVhYMC5lNFZTTVBjOWxJY214c3QwS1lVSXBIeUN1Qk1VU0ZETnNpd0ZoUW9Oalg0";
            //"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE3MzQwMDIyNDksImV4cCI6MTc2NTUzODI0OSwiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsIkdpdmVuTmFtZSI6IkpvaG5ueSIsIlN1cm5hbWUiOiJSb2NrZXQiLCJFbWFpbCI6Impyb2NrZXRAZXhhbXBsZS5jb20iLCJSb2xlIjpbIk1hbmFnZXIiLCJQcm9qZWN0IEFkbWluaXN0cmF0b3IiXX0.e4VSMPc9lIcmxst0KYUIpHyCuBMUSFDNsiwFhQoNjX4";
            //"45641061729e6eaea0ea423117b04bb801d75cd5bb9237070ab5c240ff7b855a";
            //"qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjklzxcvbnmqwert==";
            //"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE3MzM4NDY0MDMsImV4cCI6MTc2NTM4MjQwMywiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsIkdpdmVuTmFtZSI6IkpvaG5ueSIsIlN1cm5hbWUiOiJSb2NrZXQiLCJFbWFpbCI6Impyb2NrZXRAZXhhbXBsZS5jb20iLCJSb2xlIjpbIk1hbmFnZXIiLCJQcm9qZWN0IEFkbWluaXN0cmF0b3IiXX0.hk8vI5MtOcxnPXLgd1wuZIWNwVSe_h23ahM6kbJVPx8";

    public String extractUsername(String token) {

        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);

    }
    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails){
            return Jwts
                    .builder()
                    .setClaims(extraClaims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *24))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username= extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
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
