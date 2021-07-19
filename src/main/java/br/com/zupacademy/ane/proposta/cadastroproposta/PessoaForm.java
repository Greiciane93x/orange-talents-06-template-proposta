package br.com.zupacademy.ane.proposta.cadastroproposta;

import br.com.zupacademy.ane.proposta.validacao.CpfOuCnpj;

import javax.persistence.Embedded;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PessoaForm {

    @NotBlank
    @Valid @NotNull
    private String email;

    @NotBlank @NotNull
    private String nome;

    @NotNull @Positive
    private BigDecimal salario;

    @Valid
    @NotBlank @CpfOuCnpj
    private String documento;

    private Endereco endereco;

    public PessoaForm(@NotBlank @Valid @NotNull String email,
                  @NotBlank @NotNull String nome,
                  @NotBlank @NotNull @Positive BigDecimal salario,
                  @Valid @NotBlank String documento,
                  @NotBlank @NotNull Endereco endereco) {
        this.email = email;
        this.nome = nome;
        this.salario = salario;
        this.documento = documento;
        this.endereco = endereco;
    }

    public String getEmail() { return email; }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getDocumento() {
        return documento;
    }

    public Pessoa converter(EntityManager manager) {
        return new Pessoa(nome, email,salario,documento,endereco);
    }
}
