package br.com.zupacademy.ane.proposta.cadastroproposta;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.naming.Binding;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class CadastroPropostaController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping("/proposta")
    @Transactional
    public ResponseEntity<?> criaProposta(@RequestBody @Valid PessoaForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(400).build();
        }
        Pessoa pessoa = form.converter(manager);
        manager.persist(pessoa);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(pessoa.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
