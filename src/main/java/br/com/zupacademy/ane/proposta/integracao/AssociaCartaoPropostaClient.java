package br.com.zupacademy.ane.proposta.integracao;


import br.com.zupacademy.ane.proposta.associapropostacartao.PropostaVinculaCartaoForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "associacartao",  url = "http://localhost:8888")
public interface AssociaCartaoPropostaClient {

     @GetMapping("/api/cartoes")
     Map<Object, ?> associaCartaoProposta(@RequestBody PropostaVinculaCartaoForm form);

}
