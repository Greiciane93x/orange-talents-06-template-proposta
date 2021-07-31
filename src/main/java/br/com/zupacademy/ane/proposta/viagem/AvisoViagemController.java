package br.com.zupacademy.ane.proposta.viagem;

import br.com.zupacademy.ane.proposta.bloqueiocartao.BloqueioCartao;
import br.com.zupacademy.ane.proposta.cadastroproposta.Proposta;
import br.com.zupacademy.ane.proposta.cadastroproposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class AvisoViagemController {

    @Autowired
    private AvisoViagemRepository avisoViagemRepository;

    @Autowired
    private PropostaRepository propostaRepository;

    @PersistenceContext
    private EntityManager manager;

    public AvisoViagemController(AvisoViagemRepository avisoViagemRepository, PropostaRepository propostaRepository, EntityManager manager) {
        this.avisoViagemRepository = avisoViagemRepository;
        this.propostaRepository = propostaRepository;
        this.manager = manager;
    }

    @PostMapping("/aviso/viagem/{idProposta}")
    @Transactional
    public ResponseEntity<?> criaAvisoViagem (@PathVariable("idProposta") Long idProposta,
                                              @RequestBody @Valid AvisoViagemForm form, HttpServletRequest request){

        Optional<Proposta> checaProposta = propostaRepository.findById(idProposta);

        if(checaProposta.isPresent()){
            Proposta proposta = manager.find(Proposta.class, idProposta);
            AvisoViagem avisoviagem = form.converter(proposta, request);
            var avisoViagem = avisoViagemRepository.save(avisoviagem);
            return ResponseEntity.ok().body(avisoViagem);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}


//
//
//    Aviso de Viagem
//        Objetivo
//    Cadastrar um aviso de viagem para o cartão.
//
//        Necessidades
//        O portador do cartão pode realizar uma notificação para o banco, dizendo que pretende utilizar o cartão em um determinado destino, isso ajuda o banco no controle das fraudes.
//      Restrições
//        O identificador do cartão é obrigatório e deve ser informado na URL (path parameter).
//        O destino da viagem é obrigatório, ou seja, não pode ser nulo ou vazio.
//        A data do término da viagem é obrigatória, ou seja, não pode ser nulo ou uma data antiga.
//        Resultado Esperado
//        O aviso de viagem deve estar armazenado no sistema, com um identificador gerado pelo sistema.
//        Retornar 200 em caso de sucesso.
//        Retornar 400 quando violado alguma das restrições.
//        Retornar 404 quando o cartão não for encontrado.
//        Antes de começar
