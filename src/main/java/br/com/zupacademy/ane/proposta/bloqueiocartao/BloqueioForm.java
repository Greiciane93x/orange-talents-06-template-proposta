package br.com.zupacademy.ane.proposta.bloqueiocartao;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class BloqueioForm {


    @JsonIgnore
    private LocalDateTime instante = LocalDateTime.now();


    @JsonIgnore
    private String ipClient;


    @JsonIgnore
    private String userAgent;

    @JsonIgnore
    private String numeroCartao;

    private HttpServletRequest request;

    @Deprecated
    public BloqueioForm() {
    }

    public BloqueioForm(LocalDateTime instante, String ipClient, String userAgent, @NotBlank String numeroCartao,HttpServletRequest request) {
        this.instante = LocalDateTime.now();
        this.ipClient = request.getRemoteAddr();
        this.userAgent = request.getHeader("User-Agent");
        this.numeroCartao = numeroCartao;
    }

    public LocalDateTime getInstante() {
        return instante;
    }

    public String getIpClient(HttpServletRequest request) {
        return request.getRemoteAddr();
    }

    public String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public BloqueioCartao converter(EntityManager manager, String numeroCartao, HttpServletRequest request) {
       return new BloqueioCartao(instante, request.getRemoteAddr(), request.getHeader("User-Agent"),numeroCartao);
    }


}
