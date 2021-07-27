package br.com.zupacademy.ane.proposta.cadastroproposta;

import br.com.zupacademy.ane.proposta.integracao.AnalisePropostaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class PropostaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private AnalisePropostaClient analise;

    @Autowired
    private PropostaRepository repository;

    @PostMapping("/proposta")
    @Transactional
    public ResponseEntity<?> criaProposta(@RequestBody @Valid PropostaForm form) {
        Optional<Proposta> documento = repository.findByDocumento(form.getDocumento());
        if (!documento.isPresent()) {
            var retornoApi = analise.avaliaProposta(form);

            if (form.getStatusProposta() == StatusProposta.COM_RESTRICAO) {
                form.setStatus(RetornoEligibilidade.NAO_ELEGIVEL);
            } else {
                form.setStatus(RetornoEligibilidade.ELEGIVEL);
            }
            Proposta proposta = form.converter(manager);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .path("/{id}")
                    .buildAndExpand(proposta.getId())
                    .toUri();

            manager.persist(proposta);
                return ResponseEntity.status(201).body(uri);
        }
        return ResponseEntity.status(422).build();
    }
}

