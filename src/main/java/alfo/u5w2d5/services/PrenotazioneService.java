package alfo.u5w2d5.services;

import alfo.u5w2d5.entities.Dipendente;
import alfo.u5w2d5.entities.Prenotazione;
import alfo.u5w2d5.entities.Viaggio;
import alfo.u5w2d5.exceptions.NotFoundExceptions;
import alfo.u5w2d5.payloads.PrenotazioneDTO;
import alfo.u5w2d5.repositories.DipendenteRepository;
import alfo.u5w2d5.repositories.PrenotazioneRepository;
import alfo.u5w2d5.repositories.ViaggioRepository;
import alfo.u5w2d5.exceptions.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private DipendenteRepository dipendenteRepository;
    @Autowired
    private ViaggioRepository viaggioRepository;




    public Prenotazione save(PrenotazioneDTO body) {


        Dipendente dipendente = dipendenteRepository.findById(body.dipendenteId())
                .orElseThrow(() -> new NotFoundExceptions("Dipendente non trovato"));


        Viaggio viaggio = viaggioRepository.findById(body.viaggioId())
                .orElseThrow(() -> new NotFoundExceptions("Viaggio non trovato"));


        if (prenotazioneRepository.existsByDipendenteAndDataRichiesta(dipendente, body.dataRichiesta())) {
            throw new BadRequestException("Il dipendente ha già una prenotazione per questa data");
        }

        Prenotazione newPrenotazione = new Prenotazione();
        newPrenotazione.setDataRichiesta(body.dataRichiesta());
        newPrenotazione.setNote(body.note());
        newPrenotazione.setPreferenzeDipendente(body.preferenzeDipendente());
        newPrenotazione.setDipendente(dipendente);
        newPrenotazione.setViaggio(viaggio);


        return prenotazioneRepository.save(newPrenotazione);
    }


}
