package br.com.zupacademy.ane.proposta.carteiradigital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AssociaCartaoCarteiraRepositoy extends JpaRepository<AssociaCartaoCarteira, Long> {

    @Query("SELECT 1 FROM AssociaCartaoCarteira WHERE proposta_id = :idProposta")
    Optional<AssociaCartaoCarteira> buscaIdProposta(@Param("idProposta") Long idProposta);

}
