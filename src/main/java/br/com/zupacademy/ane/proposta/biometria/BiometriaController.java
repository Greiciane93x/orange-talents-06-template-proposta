package br.com.zupacademy.ane.proposta.biometria;

import br.com.zupacademy.ane.proposta.cadastroproposta.Proposta;
import br.com.zupacademy.ane.proposta.cadastroproposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class BiometriaController {

    @Autowired
    private PropostaRepository repository;

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/biometria")
    @Transactional
    public ResponseEntity<?> criaBiometria(@RequestBody @Valid BiometriaForm form) {
        Optional<Proposta> cartao = repository.findByNumeroCartao(form.getNumeroCartao());
            if (cartao.isPresent()) {
                Biometria biometria = form.converter(manager);
                manager.persist(biometria);
                return ResponseEntity.status(201).build();
            } else if (cartao.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        return  ResponseEntity.badRequest().build();
    }
}
