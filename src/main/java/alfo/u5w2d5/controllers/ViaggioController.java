package alfo.u5w2d5.controllers;

import alfo.u5w2d5.entities.Viaggio;
import alfo.u5w2d5.payloads.ViaggioDTO;
import alfo.u5w2d5.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ViaggioController {
    @Autowired
    private ViaggioService viaggioService;


//POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio createViaggio(@RequestBody ViaggioDTO body){
        return this.viaggioService.save(body);
    }

//    GET ALL
    @GetMapping
    public List<Viaggio> findAll(){
        return this.viaggioService.findAll();
    }

//GET BY ID
    @GetMapping("/{viaggioId}")
   public Viaggio findById(@PathVariable UUID viaggioId){
        return this.viaggioService.findById(viaggioId);
    }
//    UPDATE
    @PutMapping("/{viaggioId}")
    public Viaggio findByIdAndUpdate(@PathVariable UUID viaggioId, @RequestBody ViaggioDTO body){
        return this.viaggioService.findByIdAndUpdate(viaggioId,body);
    }

//    DELETE
    @DeleteMapping("/{viaggioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable UUID viaggioId){
        this.viaggioService.findByIdAndDelete(viaggioId);
    }
}
