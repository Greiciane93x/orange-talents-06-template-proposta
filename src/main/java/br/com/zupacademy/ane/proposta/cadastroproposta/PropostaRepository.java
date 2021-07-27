package br.com.zupacademy.ane.proposta.cadastroproposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    Optional<Proposta> findByNumeroCartao(String numeroCartao);

    @Query("select 1 FROM Proposta p WHERE p.documento = :documento")
    Optional<Proposta> findByDocumento(@Param("documento") String documento);

    @Query("select p from Proposta p WHERE p.status = :status")
    List<Proposta> findRetornoEligibilidade(@Param("status") RetornoEligibilidade statusProposta);

    @Modifying
    @Query(value = "UPDATE Proposta p SET p.numeroCartao = ?1 WHERE p.id = ?2")
    void saveCartao(String cartao, Long idProposta);

    Optional<Proposta> findByIdAndNumeroCartao(Long id, String numeroCartao);

    @Query(value = "SELECT p FROM Proposta p WHERE p.numeroCartao is null AND p.id is not null")
    List<Proposta> buscaIdENumeroCartao();


}