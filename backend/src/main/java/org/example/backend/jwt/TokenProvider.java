package org.example.backend.jwt;

import io.jsonwebtoken.*;
import org.example.backend.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenProvider {
    public String generateToken(Authentication authentication) {
        UserPrincipal oAuth2User = (UserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 864000000);
        return Jwts.builder()
                .setSubject(oAuth2User.getId().toString())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();

    }

    public Long getId(String token) {
        return Long.valueOf(Jwts.parser()
                .setSigningKey("secret")
                .parseClaimsJws(token)
                .getBody()
                .getSubject());
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey("secret").parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        /*catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }*/
    }
}

