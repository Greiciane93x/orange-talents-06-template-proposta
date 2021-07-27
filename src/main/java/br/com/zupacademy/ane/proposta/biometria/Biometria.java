package br.com.zupacademy.ane.proposta.biometria;

import br.com.zupacademy.ane.proposta.cadastroproposta.Proposta;

import javax.persistence.*;
import java.util.Base64;

@Entity
public class Biometria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroCartao;
    private String biometria;

    @ManyToOne
    @JoinColumn(name = "proposta_id")
    private Proposta proposta;

    public Biometria(String numeroCartao,String biometria, Proposta proposta) {
       this.numeroCartao = numeroCartao;
       this.biometria = Base64.getEncoder().encodeToString(biometria.getBytes());
       this.proposta = proposta;
    }

    public Long getId() {
        return id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getBiometria() {
        return biometria;
    }

    public Proposta getProposta() {
        return proposta;
    }
}
