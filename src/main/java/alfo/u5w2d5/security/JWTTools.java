package alfo.u5w2d5.security;

import alfo.u5w2d5.entities.Dipendente;
import alfo.u5w2d5.exceptions.UnauthorizedExceptions;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {
    @Value("${jwt.secret}")
private String secret;

    public String createToken(Dipendente dipendente) {
        // Jwts (proveniente da jjwt-api) ha principalmente 2 metodi: builder() e parser() che useremo rispettivamente per creare il token
        // e per verificare il token


        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .subject(String.valueOf(dipendente.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();

    }

    public void verifyToken(String accessToken) {
        try{
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(accessToken);
        }catch(Exception ex){
            throw new UnauthorizedExceptions("Ci sono stati errori nel token! Effettua di nuovo il login");
        }

    }
}
