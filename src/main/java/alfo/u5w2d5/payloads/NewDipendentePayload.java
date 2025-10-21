package alfo.u5w2d5.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NewDipendentePayload {
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String password;
}
