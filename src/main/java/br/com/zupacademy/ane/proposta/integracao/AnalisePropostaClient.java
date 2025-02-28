package br.com.zupacademy.ane.proposta.integracao;

import br.com.zupacademy.ane.proposta.cadastroproposta.PropostaForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "analise" , url = "${analise}")
public interface AnalisePropostaClient {

    @PostMapping("api/solicitacao")
    Map<Object,?> avaliaProposta(@RequestBody PropostaForm form);


}
