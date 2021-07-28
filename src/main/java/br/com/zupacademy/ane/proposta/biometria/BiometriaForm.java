package br.com.zupacademy.ane.proposta.biometria;

import br.com.zupacademy.ane.proposta.cadastroproposta.Proposta;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BiometriaForm {

    @JsonProperty("numeroCartao")
    @NotBlank
    private String numeroCartao;

    @JsonProperty
    @NotBlank
    private String biometria;

    @JsonProperty("idProposta")
    @NotNull
    private Long idProposta;

    public BiometriaForm(String numeroCartao,String biometria, Long idProposta) {
        this.numeroCartao = numeroCartao;
        this.biometria = biometria;
        this.idProposta = idProposta;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getBiometria() {
        return biometria;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    @Deprecated
    public BiometriaForm() {
    }

    public Biometria converter(EntityManager manager, Proposta proposta) {
        return new Biometria(numeroCartao,biometria,proposta);
    }

}
