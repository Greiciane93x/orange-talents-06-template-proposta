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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Pessoa(@NotBlank @Valid @NotNull String email,
                  @NotBlank @NotNull String nome,
                  @NotBlank @NotNull @Positive BigDecimal salario,
                  @Valid @NotBlank String documento,
                   Endereco endereco) {
        this.email = email;
        this.nome = nome;
        this.salario = salario;
        this.documento = documento;
        this.endereco = endereco;
    }

    @Deprecated
    public Pessoa() {
    }

    public Long getId() { return id; }

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

    public Endereco getEndereco() {
        return endereco;
    }
}
