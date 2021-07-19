package br.com.zupacademy.ane.proposta.validacao;

import java.util.Collection;

public class ErrorStandard {
    private Collection<String> messages;

    public ErrorStandard(Collection<String> messages) {
        this.messages = messages;
    }
}
