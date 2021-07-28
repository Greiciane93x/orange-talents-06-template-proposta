package br.com.zupacademy.ane.proposta.biometria;

import br.com.zupacademy.ane.proposta.cadastroproposta.Proposta;
import br.com.zupacademy.ane.proposta.cadastroproposta.PropostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;;
import javax.validation.Valid;
import java.net.URI;
import java.util.Base64;
import java.util.Optional;

@RestController
public class BiometriaController {

    @Autowired
    private PropostaRepository repository;

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/biometria/{id}")
    @Transactional
    public ResponseEntity<?> criaBiometria(@PathVariable("id") Long id, @RequestBody @Valid BiometriaForm form) {

        /**
         * Aqui eu busco o numero do cartão com o EntityManager
         * Depois eu serializo os itens da classe de request, que no meu caso é BiometriaForm,
         * e passo como parâmetro
         * o id da proposta
         * Com a busca do cartão passado pelo body eu valido se o número do cartão existe
         * Se ele não existir, um status code 404
         * Se ele existir, eu persisto a biometria e lanço um status code 201(created)
         * A validação para caso o body da biometria [venha nulo] está no form de request onde eu
         * passei @NotBlank
         */
             Proposta proposta = manager.find(Proposta.class, id);
             Optional<Proposta> numeroCartao = repository.findByNumeroCartao(form.getNumeroCartao());
             Biometria biometria = form.converter(manager, proposta);

                 URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .path("/{biometria}")
                    .buildAndExpand(Base64.getEncoder().encodeToString(form.getBiometria().getBytes()))
                    .toUri();

            if(numeroCartao.isPresent()){
                manager.persist(biometria);
                return ResponseEntity.status(201).header(HttpHeaders.LOCATION, String.valueOf(uri)).build();

            }else{
                return ResponseEntity.status(404).build();
            }
    }
}
