package com.ikubinfo.internship.security;

import com.ikubinfo.internship.exception.RestExceptionHandler;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenProvider implements InitializingBean {

    Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    private static final String AUTHORITIES_KEY = "auth";

    private final String base64Secret;
    private final long tokenValidityInMilliseconds;

    private Key key;

    public TokenProvider(@Value("${jwt.base64-secret}") String base64Secret, @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
        this.base64Secret = base64Secret;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 10000;
    }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(base64Secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));

        long now = (new Date()).getTime();
        Date validity = new Date(now + this.tokenValidityInMilliseconds);

        return Jwts.builder().setSubject(authentication.getName()).claim(AUTHORITIES_KEY, authorities).signWith(SignatureAlgorithm.HS512, key).setExpiration(validity).compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }
        return false;
    }
}