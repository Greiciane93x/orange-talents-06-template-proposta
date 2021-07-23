package br.com.zupacademy.ane.proposta.cadastroproposta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("select 1 FROM Pessoa p WHERE p.documento = :documento" )
    Optional<Pessoa> findDocumento(@Param("documento") String documento);

    @Query("select p from Pessoa p WHERE p.status = :status")
    List<Pessoa> findRetornoEligibilidade(@Param("status") RetornoEligibilidade statusProposta);
}
