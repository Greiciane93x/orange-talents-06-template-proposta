package br.com.zupacademy.ane.proposta.carteiradigital;

import br.com.zupacademy.ane.proposta.cadastroproposta.Proposta;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AssociaCartaoCarteiraForm {

    @NotBlank
    @Email
    private String email;

    private Carteira carteira;

    private Long idProposta;

    public AssociaCartaoCarteiraForm(String email, Carteira carteira, Long idProposta) {
        this.email = email;
        if(this.carteira == Carteira.PAYPAL){
            this.carteira = carteira.PAYPAL;
        }else{
            this.carteira = carteira.SAMSUNG_PAY;
        }
        this.idProposta = idProposta;
    }

    public String getEmail() {
        return email;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public AssociaCartaoCarteira converter(EntityManager manager) {
        Proposta proposta = manager.find(Proposta.class, idProposta);
        return new AssociaCartaoCarteira(email, carteira, proposta);
    }
}
