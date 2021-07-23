package br.com.zupacademy.ane.proposta.cadastroproposta;

import br.com.zupacademy.ane.proposta.integracao.AnalisePropostaClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Status;
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
    private PessoaRepository repository;

    @PostMapping("/proposta")
    @Transactional
    public ResponseEntity<?> criaProposta(@RequestBody @Valid PropostaForm form) {
        Optional<Pessoa> documento = repository.findDocumento(form.getDocumento());
        if (!documento.isPresent()) {
            var retornoApi = analise.avaliaProposta(form);
            Pessoa pessoa = form.converter(manager);
            geraURI(pessoa);
            try{
                defineElegibilidade(form);
                manager.persist(pessoa);
                return ResponseEntity.status(201).body(retornoApi);
            }catch (FeignException ex){
                defineElegibilidade(form);
                return ResponseEntity.status(422).build();
            }

        }
        return ResponseEntity.status(422).build();
    }


    // definindo a elegibilidade do status da proposta
    private void defineElegibilidade(PropostaForm form) {
        if (form.getStatusProposta() == StatusProposta.COM_RESTRICAO) {
            form.setStatus(RetornoEligibilidade.NAO_ELEGIVEL);
        } else {
            form.setStatus(RetornoEligibilidade.ELEGIVEL);
        }
    }

    // método privado para gera uri
    private void geraURI(Pessoa pessoa) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(pessoa.getId())
                .toUri();
    }
}

