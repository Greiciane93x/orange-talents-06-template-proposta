package br.com.zupacademy.ane.proposta.bloqueiocartao;

import br.com.zupacademy.ane.proposta.cadastroproposta.Proposta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Entity
public class BloqueioCartao {

    @Id
    @GeneratedValue
    private Long idBloqueioCartao;

    private LocalDateTime instante;
    private String ipClient;
    private String userAgent;

    @OneToOne
    private Proposta proposta;

    @Deprecated
    public BloqueioCartao() {
    }

    public BloqueioCartao(LocalDateTime instante, HttpServletRequest request) {
        this.instante = instante;
        this.ipClient = request.getRemoteAddr();
        this.userAgent = request.getHeader("User-Agent");
    }


    public BloqueioCartao(Proposta proposta, LocalDateTime instante, String ipClient, String userAgent) {
        this.proposta = proposta;
        this.instante = instante;
        this.ipClient = ipClient;
        this.userAgent = userAgent;

    }

    public Proposta getProposta() {
        return proposta;
    }

    public Long getIdBloqueioCartao() {
        return idBloqueioCartao;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public String getIpClient() { return ipClient; }

    public String getUserAgent() {
        return userAgent;
    }

}
