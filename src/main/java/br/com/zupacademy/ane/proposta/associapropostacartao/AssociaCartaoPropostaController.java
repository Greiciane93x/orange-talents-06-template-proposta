 package br.com.zupacademy.ane.proposta.associapropostacartao;

import br.com.zupacademy.ane.proposta.cadastroproposta.Pessoa;
import br.com.zupacademy.ane.proposta.cadastroproposta.PessoaRepository;
import br.com.zupacademy.ane.proposta.integracao.AssociaCartaoPropostaClient;
import br.com.zupacademy.ane.proposta.cadastroproposta.RetornoEligibilidade;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
public class AssociaCartaoPropostaController {

    @Autowired
    private AssociaCartaoPropostaClient associa;

    @Autowired
    private PessoaRepository repository;

    @PostMapping(value = "vincula/cartao")
    public ResponseEntity<?> associandoPropostaCartao(@RequestBody PropostaVinculaCartaoForm form){
        List<Pessoa> elegibilidade = repository.findRetornoEligibilidade(RetornoEligibilidade.ELEGIVEL);
        Map<Object, ?> associaPropostaCartao = associa.associaCartaoProposta(form);

        try{
            //if(!elegibilidade.isEmpty()){
                var cartao = associaPropostaCartao.get("id");
                System.out.println(cartao);
            //}
                return ResponseEntity.ok(cartao);
        }catch (FeignException ex){
            ex.responseBody();
            return ResponseEntity.status(500).build();

        }
    }
}
