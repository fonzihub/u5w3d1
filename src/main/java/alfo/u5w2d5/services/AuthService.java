package alfo.u5w2d5.services;

import alfo.u5w2d5.entities.Dipendente;
import alfo.u5w2d5.exceptions.UnauthorizedExceptions;
import alfo.u5w2d5.payloads.LoginDTO;
import alfo.u5w2d5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private JWTTools jwtTools;

    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        // 1. Controllo credenziali
        // 1.1 Controllo nel DB se esiste un utente con quell'indirizzo email (fornito nel body)
        Dipendente found = this.dipendenteService.findByEmail(body.email());

        // 1.2 Se esiste verifico che la sua password corrisponda a quella del body
        // 1.3 Se una delle 2 verifiche non va a buon fine --> 401
        if (found.getPassword().equals(body.password())) {
            // TODO: Migliorare gestione password
            // 2. Se credenziali OK --> Genero un access token
            // 3. Ritorno il token
            return jwtTools.createToken(found);
        } else {
            throw new UnauthorizedExceptions("Credenziali errate!");
        }

    }
}
