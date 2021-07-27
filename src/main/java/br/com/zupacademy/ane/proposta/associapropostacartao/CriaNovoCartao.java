package br.com.zupacademy.ane.proposta.associapropostacartao;

import br.com.zupacademy.ane.proposta.cadastroproposta.Proposta;
import br.com.zupacademy.ane.proposta.cadastroproposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Component
@EnableScheduling
public class CriaNovoCartao {

    @Autowired
    private PropostaRepository repository;

    @PersistenceContext
    private EntityManager manager;


    public CriaNovoCartao() {
    }

    @Scheduled(fixedDelay = 5000)
    public void criaNovoCartaoMedianteProposta() {
        try {
            Object lista = repository.buscaIdENumeroCartao();
            System.out.println(lista);
        }catch (IllegalThreadStateException ex){
            ex.getCause();
        }
    }

}
