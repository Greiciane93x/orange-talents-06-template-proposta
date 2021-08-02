 package br.com.zupacademy.ane.proposta.associapropostacartao;

import br.com.zupacademy.ane.proposta.cadastroproposta.Proposta;
import br.com.zupacademy.ane.proposta.cadastroproposta.PropostaRepository;
import br.com.zupacademy.ane.proposta.integracao.AssociaCartaoPropostaClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@RestController
public class AssociaCartaoPropostaController {

    @Autowired
    private AssociaCartaoPropostaClient associa;

    @Autowired
    private PropostaRepository repository;

    @PostMapping(value = "vincula/cartao")
    @Transactional
    public ResponseEntity<?> associandoPropostaCartao(@RequestBody PropostaVinculaCartaoForm form) {


        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);


        Map<Object, ?> associaPropostaCartao = associa.associaCartaoProposta(form);
        try {
            Optional<Proposta> proposta = repository.findByIdAndNumeroCartao(form.getIdProposta(), form.getId());
            if (proposta.isPresent()) {
                String resultDocumento = (String) associaPropostaCartao.get("id");
                repository.saveCartao(encoder.encode(resultDocumento), form.getIdProposta());
                return ResponseEntity.ok().build();
            }

        } catch (FeignException ex) {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(400).build();
    }
}
