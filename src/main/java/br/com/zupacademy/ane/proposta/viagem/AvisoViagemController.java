package br.com.zupacademy.ane.proposta.viagem;

import br.com.zupacademy.ane.proposta.bloqueiocartao.BloqueioCartao;
import br.com.zupacademy.ane.proposta.cadastroproposta.Proposta;
import br.com.zupacademy.ane.proposta.cadastroproposta.PropostaRepository;
import br.com.zupacademy.ane.proposta.integracao.AssociaCartaoPropostaClient;
import feign.FeignException;
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

    @Autowired
    private AssociaCartaoPropostaClient analise;

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

    @PostMapping("/aviso/viagem/{idProposta}/client")
    @Transactional ResponseEntity<?> criaAvisoViagemServicoExterno(@PathVariable("idProposta") Long idProposta,
                                                                   @RequestBody @Valid VerificaAvisoViagemForm form,
                                                                   HttpServletRequest request){

            try {
                var retornaCli = analise.verificaViagem(idProposta, form);
                Proposta proposta = manager.find(Proposta.class, idProposta);
                AvisoViagem avisoViagem = form.converter(proposta, request);
                var avisoviagem= avisoViagemRepository.save(avisoViagem);
                return ResponseEntity.ok().body(avisoViagem);
            }catch (FeignException ex){
                ex.getCause();
            }
            return ResponseEntity.status(400).build();
        }

}


