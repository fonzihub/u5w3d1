package alfo.u5w2d5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Prenotazione {
    @Id
    @GeneratedValue
    @Column(name = "id_prenotazione")
    private UUID id;
    @ManyToOne
    @JoinColumn
    private Viaggio viaggio;

    @ManyToOne
    @JoinColumn
    private Dipendente dipendente;

    private LocalDate dataRichiesta;
    private String note;
    private String preferenzeDipendente;

    public Prenotazione(Viaggio viaggio, Dipendente dipendente, LocalDate dataRichiesta, String note, String preferenzeDipendente) {
        this.viaggio = viaggio;
        this.dipendente = dipendente;
        this.dataRichiesta = dataRichiesta;
        this.note = note;
        this.preferenzeDipendente = preferenzeDipendente;
    }
}
