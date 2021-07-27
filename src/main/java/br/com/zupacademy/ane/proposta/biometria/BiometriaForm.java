package br.com.zupacademy.ane.proposta.biometria;

import br.com.zupacademy.ane.proposta.cadastroproposta.Proposta;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.EntityManager;

public class BiometriaForm {


    @JsonProperty("numeroCartao")
    private String numeroCartao;

    @JsonProperty("biometria")
    private String biometria;
    //@NotNull
    @JsonProperty("idProposta")
    private Long idProposta;
    //@NotBlank
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

    public Biometria converter(EntityManager manager) {
        Proposta proposta = manager.find(Proposta.class, idProposta);
        return new Biometria(numeroCartao,biometria,proposta);
    }

}
