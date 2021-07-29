package br.com.zupacademy.ane.proposta.bloqueiocartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloqueioCartaoRepository extends JpaRepository<BloqueioCartao, Long> {
    Optional<BloqueioCartao> findByNumeroCartao(String cartao);
}
