package br.com.zupacademy.ane.proposta.cadastroproposta;

import br.com.zupacademy.ane.proposta.validacao.CpfOuCnpj;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PropostaForm {

    @NotNull
    private Long idProposta;

    @NotBlank @NotNull
    private String nome;

    @Valid @NotBlank @CpfOuCnpj
    private String documento;

    @NotBlank
    private String email;

    @NotNull
    private BigDecimal salario;

    private Endereco endereco;

    private RetornoEligibilidade status;

    private StatusProposta statusProposta;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
    String resultDocumento = encoder.encode(documento);

    public PropostaForm(Long idProposta, String nome, String documento,
                        String email, BigDecimal salario, StatusProposta status,
                        Endereco endereco) {
        this.idProposta = idProposta;
        this.nome = nome;
        this.documento = resultDocumento;
        this.email = email;
        this.salario = salario;
        this.statusProposta = statusProposta;
        this.endereco = endereco;
    }

    public PropostaForm(Long idProposta, String nome, String documento, String email,
                        BigDecimal salario, Endereco endereco, RetornoEligibilidade status) {
        this.idProposta = idProposta;
        this.nome = nome;
        this.documento = resultDocumento;
        this.email = email;
        this.salario = salario;
        this.endereco = endereco;
        this.status = status;
    }

    @Deprecated
    public PropostaForm() {
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public RetornoEligibilidade getStatus() {
        return status;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    // precisei acrescentar esse set para mudar a elegibilidade
    // caso tenha ou não restrição
    public void setStatus(RetornoEligibilidade status) {
        this.status = status;
    }

    public Proposta converter(EntityManager manager) {
        return new Proposta(idProposta,email, nome,salario, documento,endereco,status);
    }
}