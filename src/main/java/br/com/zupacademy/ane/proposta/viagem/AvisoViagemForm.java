package br.com.zupacademy.ane.proposta.viagem;

import br.com.zupacademy.ane.proposta.cadastroproposta.Proposta;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class AvisoViagemForm {

    @NotNull
    private LocalDate instanteViagem;
    @NotBlank
    private String ipClient;
    @NotBlank
    private String userAgent;
    @NotBlank
    private String destinoViagem;
    @NotNull
    @Future
    private LocalDate terminoViagem;


    @Deprecated
    public AvisoViagemForm() {
    }

    public AvisoViagemForm(LocalDate instanteViagem, String ipClient, String userAgent, String destinoViagem, LocalDate terminoViagem) {
        this.instanteViagem = LocalDate.now();
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.destinoViagem = destinoViagem;
        this.terminoViagem = terminoViagem;
    }


    public LocalDate getInstanteViagem() {
        return instanteViagem;
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }

    public LocalDate getTerminoViagem() {
        return terminoViagem;
    }

    public String getIpClient(HttpServletRequest request) { return request.getRemoteAddr(); }

    public String getUserAgent(HttpServletRequest request) { return request.getHeader("User-Agent"); }

    public AvisoViagem converter(Proposta proposta, HttpServletRequest request) {
        return new AvisoViagem(LocalDate.now(), request.getRemoteAddr(), request.getHeader("User-Agent"), destinoViagem, terminoViagem, proposta);
    }


}
