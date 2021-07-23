package br.com.zupacademy.ane.proposta.cadastroproposta;

import br.com.zupacademy.ane.proposta.validacao.CpfOuCnpj;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class Pessoa {
    @Id
    private Long id;

    @NotBlank @Valid @NotNull
    private String email;

    @NotBlank @NotNull
    private String nome;

    @NotNull @Positive
    private BigDecimal salario;

    @Valid @NotBlank @CpfOuCnpj
    @Column(unique = true)
    private String documento;

    @Embedded
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    private RetornoEligibilidade status;

    private String NumeroCartao;

    public Pessoa(Long id, String email, String nome, BigDecimal salario,
                  String documento, Endereco endereco, RetornoEligibilidade status) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.salario = salario;
        this.documento = documento;
        this.endereco = endereco;
        this.status = status;
    }
    @Deprecated
    public Pessoa() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public RetornoEligibilidade getStatus() { return status; }

    public String getEmail() { return email; }

    public BigDecimal getSalario() { return salario; }

    public Endereco getEndereco() { return endereco; }

    public String getNumeroCartao() { return NumeroCartao; }
}
