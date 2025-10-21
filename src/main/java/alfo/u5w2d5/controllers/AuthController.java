package alfo.u5w2d5.controllers;

import alfo.u5w2d5.entities.Dipendente;
import alfo.u5w2d5.exceptions.ValidationException;
import alfo.u5w2d5.payloads.LoginDTO;
import alfo.u5w2d5.payloads.LoginResponseDTO;
import alfo.u5w2d5.payloads.NewDipendentePayload;
import alfo.u5w2d5.services.AuthService;
import alfo.u5w2d5.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private DipendenteService dipendenteService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO body) {
        return new LoginResponseDTO(authService.checkCredentialsAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente createUser(@RequestBody @Validated NewDipendentePayload payload, BindingResult validationResult) {
        // @Validated serve per "attivare" la validazione
        // BindingResult è un oggetto che contiene tutti gli errori e anche dei metodi comodi da usare tipo .hasErrors()
        if (validationResult.hasErrors()) {

            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }
        return this.dipendenteService.save(payload);
    }}
