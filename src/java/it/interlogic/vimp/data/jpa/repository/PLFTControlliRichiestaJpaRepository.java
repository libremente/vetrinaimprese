package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTControlliRichiestaEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTControlliRichiestaJpaRepository extends PagingAndSortingRepository<PLFTControlliRichiestaEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_CONTROLLI_RICHIESTA a where a.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTControlliRichiestaEntity> findControlliRichiesta();
	
	@Query(value = "SELECT a from PLFTControlliRichiestaEntity a where a.codice = :codice")
	public abstract List<PLFTControlliRichiestaEntity> findControlliRichiestaByCodice(@Param("codice") String codice);

}
