package alfo.u5w2d5.payloads;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record PrenotazioneDTO(
        @NotNull(message = "La data richiesta è obbligatoria")
        @FutureOrPresent(message = "la data richiesta non può essere nel passato")
        LocalDate dataRichiesta,
        String note,
        String preferenzeDipendente,
        UUID dipendenteId,
        UUID viaggioId
        ) {
}
