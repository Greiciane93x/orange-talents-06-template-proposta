package br.com.zupacademy.ane.proposta.bloqueiocartao;

public class SistemaBloqueioForm {

    private String sistemaResponsavel;


    @Deprecated
    public SistemaBloqueioForm() {
    }

    public SistemaBloqueioForm(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
