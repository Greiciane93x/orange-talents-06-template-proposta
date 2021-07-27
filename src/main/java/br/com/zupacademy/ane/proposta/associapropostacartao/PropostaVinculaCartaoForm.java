package br.com.zupacademy.ane.proposta.associapropostacartao;

import br.com.zupacademy.ane.proposta.cadastroproposta.Proposta;
import br.com.zupacademy.ane.proposta.cadastroproposta.PropostaRepository;
import br.com.zupacademy.ane.proposta.validacao.ExistsById;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;

public class PropostaVinculaCartaoForm {

    @Autowired
    private PropostaRepository repository;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @ExistsById(domainClass = Proposta.class, fieldName = "id")
    @NotNull
    private Long idProposta;

    private String id;

    public PropostaVinculaCartaoForm(Long idProposta,String id) {
        this.idProposta = idProposta;
        this.id = id;
    }

    @Deprecated
    public PropostaVinculaCartaoForm() {
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public String getId() { return id; }

}
