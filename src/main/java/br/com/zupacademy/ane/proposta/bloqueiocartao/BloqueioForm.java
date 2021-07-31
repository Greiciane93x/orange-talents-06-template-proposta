package br.com.zupacademy.ane.proposta.bloqueiocartao;

import br.com.zupacademy.ane.proposta.cadastroproposta.Proposta;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class BloqueioForm {


    @JsonIgnore
    private LocalDateTime instante = LocalDateTime.now();

    @JsonIgnore
    private Long idBloqueio;

    @JsonIgnore
    private String ipClient;

    @JsonIgnore
    private String userAgent;

    @JsonIgnore
    private Long idProposta;

    private HttpServletRequest request;

    @PersistenceContext
    private EntityManager manager;

    @Deprecated
    public BloqueioForm() {
    }

    public BloqueioForm(Long idProposta,Long idBloqueio,LocalDateTime instante,
                        String ipClient, String userAgent, HttpServletRequest request) {
        this.idProposta = idProposta;
        this.idBloqueio = idBloqueio;
        this.instante = LocalDateTime.now();
        this.ipClient = request.getRemoteAddr();
        this.userAgent = request.getHeader("User-Agent");

    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public Long getIdBloqueio() { return idBloqueio; }

    public String getIpClient(HttpServletRequest request) { return request.getRemoteAddr(); }

    public String getUserAgent(HttpServletRequest request) { return request.getHeader("User-Agent"); }

    public BloqueioCartao converter(Proposta proposta, HttpServletRequest request) {
       return new BloqueioCartao(proposta,instante, request.getRemoteAddr(), request.getHeader("User-Agent"));
    }

}
