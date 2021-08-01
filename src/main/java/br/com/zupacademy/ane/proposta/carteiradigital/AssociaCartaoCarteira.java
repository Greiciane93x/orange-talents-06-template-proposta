package br.com.zupacademy.ane.proposta.carteiradigital;

import br.com.zupacademy.ane.proposta.cadastroproposta.Proposta;

import javax.persistence.*;

@Entity
public class AssociaCartaoCarteira {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCarteira;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Carteira carteira;

    @ManyToOne
    private Proposta proposta;

    public AssociaCartaoCarteira(String email, Carteira carteira, Proposta proposta) {
        this.email = email;
        this.carteira = carteira;
        this.proposta = proposta;
    }

    public Long getIdCarteira() {
        return idCarteira;
    }

    public String getEmail() {
        return email;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public Carteira getAssociaCarteira() {
        return carteira;
    }
}
