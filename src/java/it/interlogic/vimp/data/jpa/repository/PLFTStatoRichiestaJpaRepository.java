package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTStatoRichiestaEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTStatoRichiestaJpaRepository extends PagingAndSortingRepository<PLFTStatoRichiestaEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_STATO_RICHIESTA a where a.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTStatoRichiestaEntity> findStatoRichiesta();
	
	@Query(value = "SELECT a from PLFTStatoRichiestaEntity a where a.codice = :codice")
	public abstract List<PLFTStatoRichiestaEntity> findStatoRichiestaByCodice(@Param("codice") String codice);

}
