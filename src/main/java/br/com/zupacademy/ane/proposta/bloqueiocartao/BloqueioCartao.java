package br.com.zupacademy.ane.proposta.bloqueiocartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class BloqueioCartao {

    @Id
    @GeneratedValue
    private Long idBloqueioCartao;

    private LocalDateTime instante;
    private String ipClient;
    private String userAgent;
    private String numeroCartao;

    @Deprecated
    public BloqueioCartao() {
    }

    public BloqueioCartao(LocalDateTime instante, String ipClient, String userAgent, String numeroCartao) {
        this.instante = instante;
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.numeroCartao = numeroCartao;
    }

    public Long getIdBloqueioCartao() {
        return idBloqueioCartao;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public String getIpClient() {
        return ipClient;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
