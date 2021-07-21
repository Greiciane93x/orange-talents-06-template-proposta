package br.com.zupacademy.ane.proposta.integracao;

import br.com.zupacademy.ane.proposta.cadastroproposta.Pessoa;

public class DadosSolicitacaoDto {

    private String documento;
    private String nome;
    private RetornoEligibilidade retorno;
    private Long idProposta;
    private Pessoa pessoa;
    private StatusProposta status;

    public DadosSolicitacaoDto(String nome, String documento, StatusProposta status, Long idProposta) {
        this.nome = pessoa.getNome();
        this.documento = pessoa.getDocumento();
        this.status = status;
        this.idProposta = idProposta;
    }

    @Deprecated
    public DadosSolicitacaoDto() {
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public RetornoEligibilidade getRetorno() {
        return retorno;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
