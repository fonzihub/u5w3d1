package alfo.u5w2d5.services;

import alfo.u5w2d5.entities.StatoViaggio;
import alfo.u5w2d5.entities.Viaggio;
import alfo.u5w2d5.exceptions.NotFoundExceptions;
import alfo.u5w2d5.payloads.ViaggioDTO;
import alfo.u5w2d5.repositories.ViaggioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ViaggioService {
    @Autowired
    private ViaggioRepository viaggioRepository;


    //    CREATE
    public Viaggio save(ViaggioDTO body) {
        Viaggio newViaggio = new Viaggio(body.destinazione(), body.data(), StatoViaggio.IN_PROGRAMMA);
        Viaggio savedViaggio = this.viaggioRepository.save(newViaggio);
        System.out.println("viaggio con id: " + savedViaggio.getId() + " è stato salvato");
return savedViaggio;

    }


//    GET ALL
    public List<Viaggio> findAll(){
        return this.viaggioRepository.findAll();
    }

//    GET ID

    public Viaggio findById(UUID viaggioId){
        return this.viaggioRepository.findById(viaggioId).orElseThrow(()->
                new NotFoundExceptions(viaggioId));
    }

//    UPDATE
    public Viaggio findByIdAndUpdate(UUID viaggioId, ViaggioDTO body){
//        cerco viaggio
        Viaggio found = this.findById(viaggioId);
//        modifico
        found.setData(body.data());
        found.setDestinazione(body.destinazione());
//        salvo
        Viaggio modifiedViaggio = this.viaggioRepository.save(found);
        log.info("viaggio con id: " + viaggioId + " è stato salvato");
        return modifiedViaggio;


    }

//    DELETE
    public void findByIdAndDelete(UUID viaggioId){
        Viaggio found = this.findById(viaggioId);
        this.viaggioRepository.delete(found);
    }


    }
