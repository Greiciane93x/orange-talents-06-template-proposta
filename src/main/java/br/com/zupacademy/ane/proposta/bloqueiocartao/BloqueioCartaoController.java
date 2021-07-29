package br.com.zupacademy.ane.proposta.bloqueiocartao;

import br.com.zupacademy.ane.proposta.cadastroproposta.Proposta;
import br.com.zupacademy.ane.proposta.cadastroproposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class BloqueioCartaoController {

    @Autowired
    private BloqueioCartaoRepository bloqueioRepository;

    @Autowired
    private PropostaRepository cartaoRepository;

    @PersistenceContext
    private EntityManager manager;


    @PostMapping("/bloqueio/{cartao}")
    @Transactional
    public ResponseEntity<?> bloqueiaCartao(@PathVariable("cartao") String cartao, @Valid BloqueioForm form, HttpServletRequest request) {

        Optional<BloqueioCartao> resultBloqueioCartao = bloqueioRepository.findByNumeroCartao(cartao);
        Optional<Proposta> resultBuscaCartao = cartaoRepository.findByNumeroCartao(cartao);

        if(!resultBuscaCartao.isPresent()){
            return ResponseEntity.status(404).build();
        }
        if (resultBloqueioCartao.isPresent()) {
            return ResponseEntity.status(422).build();
        }
        BloqueioCartao bloqueioCartao = form.converter(manager, cartao, request);
        manager.persist(bloqueioCartao);
        return ResponseEntity.status(200).build();

    }
}
//        Objetivo
//        Realizar o bloqueio do cartão.
//
//        Necessidades
//        O usuário do cartão pode realizar o bloqueio do cartão por alguma suspeita de fraude. x
//
//        Informar o identificador do cartão a ser bloqueado. x
//
//
//        Restrições
//
//        Caso o cartão esteja já bloqueado devemos retornar um erro de negócio.
//
//        Resultado Esperado
//        Bloqueio deve estar armazenada no sistema, com um identificador gerado pelo sistema.
//
//
//        Retornar 400 quando violado alguma das restrições.
//
