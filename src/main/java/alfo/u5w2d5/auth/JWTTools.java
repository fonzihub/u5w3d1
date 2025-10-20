package alfo.u5w2d5.auth;

import alfo.u5w2d5.exceptions.UnauthorizedExceptions;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JWTTools {
    @Value()

    public void verifyToken(String token) throws UnauthorizedExceptions {
        try {
            Jwts.builder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
        } catch (Exception err) {
            throw new UnauthorizedExceptions("Token invalid");
        }
    }


}
