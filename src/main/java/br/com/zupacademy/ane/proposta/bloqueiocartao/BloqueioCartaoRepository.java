package br.com.zupacademy.ane.proposta.bloqueiocartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BloqueioCartaoRepository extends JpaRepository<BloqueioCartao, Long> {

    @Query("SELECT 1 FROM BloqueioCartao WHERE proposta_id = :idProposta")
    Optional<BloqueioCartao> buscaIdProposta(@Param("idProposta")Long idProposta);
}
