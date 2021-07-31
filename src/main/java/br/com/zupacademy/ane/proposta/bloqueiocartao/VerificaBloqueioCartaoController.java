package br.com.zupacademy.ane.proposta.bloqueiocartao;

import br.com.zupacademy.ane.proposta.cadastroproposta.Proposta;
import br.com.zupacademy.ane.proposta.integracao.AssociaCartaoPropostaClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@RestController
public class VerificaBloqueioCartaoController {

    @Autowired
    private AssociaCartaoPropostaClient analise;

    @Autowired
    private BloqueioCartaoRepository bloqueioCartaoRepository;

    @PersistenceContext
    private EntityManager manager;


    @PostMapping("/testaCli/{id}")
    @Transactional
    public ResponseEntity<?> criaTestC(@PathVariable("id") Long idProposta,
                                       @RequestBody SistemaBloqueioForm formBloqueio,
                                       HttpServletRequest request, BloqueioForm form){
        try {
            var retornaCli = analise.verificaBloqueio(idProposta, formBloqueio);
            Proposta proposta = manager.find(Proposta.class, idProposta);
            BloqueioCartao bloqueioCartao = form.converter(proposta, request);
            var salvaBloqueio = bloqueioCartaoRepository.save(bloqueioCartao);
            return ResponseEntity.ok().body(salvaBloqueio);
        }catch (FeignException ex){
            ex.getCause();
        }
        return ResponseEntity.status(400).build();
    }
}
