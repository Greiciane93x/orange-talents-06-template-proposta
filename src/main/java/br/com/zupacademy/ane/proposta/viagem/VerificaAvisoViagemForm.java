package br.com.zupacademy.ane.proposta.viagem;

import br.com.zupacademy.ane.proposta.cadastroproposta.Proposta;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class VerificaAvisoViagemForm {

    private String destino;
    private LocalDate validoAte;

    public VerificaAvisoViagemForm(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = LocalDate.now();
    }

    @Deprecated
    public VerificaAvisoViagemForm() {
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public AvisoViagem converter(Proposta proposta, HttpServletRequest request) {
        return new AvisoViagem(destino, validoAte);
    }
}
