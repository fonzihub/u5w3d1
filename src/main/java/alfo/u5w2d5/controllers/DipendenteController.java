package alfo.u5w2d5.controllers;

import alfo.u5w2d5.entities.Dipendente;
import alfo.u5w2d5.payloads.NewDipendentePayload;
import alfo.u5w2d5.repositories.DipendenteRepository;
import alfo.u5w2d5.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dipendente")
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;

    //POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente createDipendente(@RequestBody NewDipendentePayload body) {
        return this.dipendenteService.save(body);
    }


    //GET ALL
    @GetMapping
    public List<Dipendente> findAll() {
        return this.dipendenteService.findAll();
    }


// GET BY ID

@GetMapping("/{dipendenteId}")
public Dipendente findById(@PathVariable UUID dipendenteId){
        return this.dipendenteService.findById(dipendenteId);
}

//    PUT
    @PutMapping
    public Dipendente findByIdAndUpdate(@PathVariable UUID dipendenteId,@RequestBody NewDipendentePayload body){
        return this.dipendenteService.findByIdAndUpdate(dipendenteId,body);
    }

//DELETE
@DeleteMapping("/{dipendenteId}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void findByIdAndDelete(@PathVariable UUID dipendenteId){
         this.dipendenteService.findByIdAndDelete(dipendenteId);
}


}