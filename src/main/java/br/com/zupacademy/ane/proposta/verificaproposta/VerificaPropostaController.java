package br.com.zupacademy.ane.proposta.verificaproposta;

import br.com.zupacademy.ane.proposta.cadastroproposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificaPropostaController {

    @Autowired
    private PropostaRepository repository;

    @GetMapping("verifica/{id}")
    private ResponseEntity<?> verificaDadosProposta(@PathVariable Long id) {
           var lista = repository.findById(id);
           if(lista.isPresent()){
               return ResponseEntity.ok().body(lista);
           }
           return ResponseEntity.status(404).build();
    }
}
