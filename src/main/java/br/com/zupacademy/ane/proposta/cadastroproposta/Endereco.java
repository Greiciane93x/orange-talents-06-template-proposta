package br.com.zupacademy.ane.proposta.cadastroproposta;

import javax.persistence.Embeddable;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Endereco {
    @NotBlank @Valid
    private String logradouro;
    @NotBlank @Valid
    private Long numero;
    @NotBlank @Valid
    private String bairro;
    @NotBlank @Valid
    private String cidade;

    public Endereco(String logradouro, Long numero, String bairro, String cidade) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    @Deprecated
    public Endereco() {
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
}
