package br.com.zupacademy.ane.proposta.integracao;

import br.com.zupacademy.ane.proposta.associapropostacartao.PropostaVinculaCartaoForm;
import br.com.zupacademy.ane.proposta.bloqueiocartao.SistemaBloqueioForm;
import br.com.zupacademy.ane.proposta.viagem.AvisoViagemForm;
import br.com.zupacademy.ane.proposta.viagem.VerificaAvisoViagemForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "associacartao",  url = "${contas}")
public interface AssociaCartaoPropostaClient {

     @GetMapping("/api/cartoes")
     Map<Object, ?> associaCartaoProposta(@RequestBody PropostaVinculaCartaoForm form);

     @PostMapping("/api/cartoes/{id}/bloqueios")
     Map<Object,?> verificaBloqueio(@PathVariable("id") Long idProposta, @RequestBody SistemaBloqueioForm form);

     @PostMapping("/api/cartoes/{id}/avisos")
     Map<Object,?> verificaViagem(@PathVariable("id") Long idProposta, @RequestBody VerificaAvisoViagemForm form);

}