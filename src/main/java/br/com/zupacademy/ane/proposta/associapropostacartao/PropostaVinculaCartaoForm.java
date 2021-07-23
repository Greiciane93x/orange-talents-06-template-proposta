package br.com.zupacademy.ane.proposta.associapropostacartao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class PropostaVinculaCartaoForm {
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Long idProposta;

    public PropostaVinculaCartaoForm(Long idProposta) {
        this.idProposta = idProposta;
    }
    @Deprecated
    public PropostaVinculaCartaoForm() {
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
