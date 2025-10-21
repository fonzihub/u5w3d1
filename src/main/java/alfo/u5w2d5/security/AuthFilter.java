package alfo.u5w2d5.security;

import alfo.u5w2d5.exceptions.UnauthorizedExceptions;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class AuthFilter extends OncePerRequestFilter {
@Autowired
private JWTTools jwtTools;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException{
        String authToken = request.getHeader("Authorization");
        if(authToken == null || !authToken.startsWith("Bearer")){
            throw new UnauthorizedExceptions("Token missing or malformed");
        }
//  Se l'header esiste, estraiamo il token da esso
//        String accessToken = authHeader.replace("Bearer", "");
//  Verifichiamo se il token è valido
//        jwtTools.verifyToken(accessToken);
//  Se tutto è OK passiamo la richiesta al prossimo (che può essere o un altro filtro o direttamente il controller)
        filterChain.doFilter(request,response);

//        @Override
//        protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//            return new AntPathMatcher().match("/auth/**", request.getServletPath());
//
//    }
}}
