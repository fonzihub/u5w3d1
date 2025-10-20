package alfo.u5w2d5.controllers;

import alfo.u5w2d5.entities.Prenotazione;
import alfo.u5w2d5.payloads.PrenotazioneDTO;
import alfo.u5w2d5.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazione")
public class PrenotazioneController {
    @Autowired
    private DipendenteService dipendenteService;


//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Prenotazione createPrenotazione(@RequestBody PrenotazioneDTO body){
//        return this.dipendenteService.save(body)
//    }
//
//    @GetMapping
//    public List<Prenotazione> findAll(){
//        return this.prenotazioneService.findAll();
//    }
}
