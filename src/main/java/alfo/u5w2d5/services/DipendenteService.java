package alfo.u5w2d5.services;

import alfo.u5w2d5.entities.Dipendente;
import alfo.u5w2d5.exceptions.NotFoundExceptions;
import alfo.u5w2d5.payloads.NewDipendentePayload;
import alfo.u5w2d5.repositories.DipendenteRepository;
import lombok.extern.slf4j.Slf4j;
import alfo.u5w2d5.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class DipendenteService {
    @Autowired
    DipendenteRepository dipendenteRepository;


    //    CREATE
    public Dipendente save(NewDipendentePayload body) {
//        verifica email se esiste
        this.dipendenteRepository.findByEmail(body.getEmail()).ifPresent(user -> {
                    try {
                        throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
                    } catch (BadRequestException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        Dipendente newDipendente = new Dipendente(body.getUsername(), body.getNome(), body.getCognome(), body.getEmail());
//SALVO
        Dipendente savedDipendente = this.dipendenteRepository.save(newDipendente);
// 4. Log
        log.info("L'utente con id: " + savedDipendente.getId() + " è stato salvato correttamente!");
        return savedDipendente;
    }


//    GET ALL
    public List<Dipendente> findAll(){
        return this.dipendenteRepository.findAll();
    }

//    GET ID
    public Dipendente findById(UUID dipendenteId){
        return this.dipendenteRepository.findById(dipendenteId).orElseThrow(()-> new NotFoundExceptions(dipendenteId));
    }

//    UPDATE
    public Dipendente findByIdAndUpdate(UUID dipendenteId, NewDipendentePayload body){
        Dipendente found = this.findById(dipendenteId);
        if(!found.getEmail().equals(body.getEmail())){
            this.dipendenteRepository.findByEmail(body.getEmail()).ifPresent(dipendente -> {
                throw new BadRequestException("email gia in uso");
            });
        }
        found.setUsername(body.getUsername());
        found.setNome(body.getNome());
        found.setCognome(body.getCognome());
        found.setEmail(body.getEmail());
//        SALVO
        Dipendente modifiedDipendente = this.dipendenteRepository.save(found);
        return modifiedDipendente;
    }

//    DELETE
    public void findByIdAndDelete(UUID dipendenteId){
        Dipendente found = this.findById(dipendenteId);
        this.dipendenteRepository.delete(found);

    }

}
