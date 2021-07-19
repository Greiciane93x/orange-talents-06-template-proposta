package br.com.zupacademy.ane.proposta.cadastroproposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class CadastroPropostaController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private PessoaRepository repository;

    @PostMapping("/proposta")
    @Transactional
    public ResponseEntity<?> criaProposta(@RequestBody @Valid PessoaForm form) {

        Optional<Pessoa> documento =  repository.findPessoa(form.getDocumento());

       if(!documento.isPresent()) {

            Pessoa pessoa = form.converter(manager);
            manager.persist(pessoa);

            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .path("/{id}")
                    .buildAndExpand(pessoa.getId())
                    .toUri();

            return ResponseEntity.created(uri).build();
        }
        return ResponseEntity.status(422).build();
    }

}