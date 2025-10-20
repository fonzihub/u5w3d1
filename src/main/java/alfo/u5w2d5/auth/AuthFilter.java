package alfo.u5w2d5.auth;

import alfo.u5w2d5.exceptions.UnauthorizedExceptions;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class AuthFilter extends OncePerRequestFilter {



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, UnauthorizedExceptions{
        String authToken = request.getHeader("Authorization");
        if(authToken == null || !authToken.startsWith("Bearer")){
            throw new UnauthorizedExceptions("Token missing or malformed");
        }

        String token = authToken.substring(7);



    }
}
