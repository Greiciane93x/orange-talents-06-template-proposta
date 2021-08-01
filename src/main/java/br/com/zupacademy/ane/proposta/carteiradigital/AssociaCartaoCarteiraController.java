package br.com.zupacademy.ane.proposta.carteiradigital;

import br.com.zupacademy.ane.proposta.cadastroproposta.Proposta;
import br.com.zupacademy.ane.proposta.cadastroproposta.PropostaRepository;
import br.com.zupacademy.ane.proposta.integracao.AssociaCartaoPropostaClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
public class AssociaCartaoCarteiraController {

    @PersistenceContext
    private EntityManager manager;

    private PropostaRepository propostaRepository;

    private AssociaCartaoCarteiraRepositoy associaCartaoRepository;

    private AssociaCartaoPropostaClient verificaAssociacao;



    @Autowired
    public AssociaCartaoCarteiraController(EntityManager manager, PropostaRepository propostaRepository,
                                           AssociaCartaoCarteiraRepositoy associaCartaoRepository, AssociaCartaoPropostaClient verificaAssociacao) {
        this.manager = manager;
        this.propostaRepository = propostaRepository;
        this.associaCartaoRepository = associaCartaoRepository;
        this.verificaAssociacao = verificaAssociacao;
    }


    @PostMapping("/associa/cartao/carteira/{id}")
    @Transactional
    public ResponseEntity<?> associaCarteira(@PathVariable("id") Long id, @RequestBody @Valid AssociaCartaoCarteiraForm form) {

        try {
            var apiExterna = verificaAssociacao.verificaAssociacao(id, form);

            Optional<AssociaCartaoCarteira> buscandoAssociacao = associaCartaoRepository.buscaIdProposta(id);

            if (buscandoAssociacao.isPresent()) {
                return ResponseEntity.unprocessableEntity().build();
            }
            AssociaCartaoCarteira associaCartaoCarteira = form.converter(manager);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .path("/{id}")
                    .buildAndExpand(form.getCarteira())
                    .toUri();

            Optional<Proposta> proposta = propostaRepository.findById(id);
            if (proposta.isPresent()) {
                manager.persist(associaCartaoCarteira);
                return ResponseEntity.status(201).header(HttpHeaders.LOCATION, String.valueOf(uri)).build();
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (FeignException ex) {
            ex.getCause();
        }
        return ResponseEntity.badRequest().build();
    }

}
