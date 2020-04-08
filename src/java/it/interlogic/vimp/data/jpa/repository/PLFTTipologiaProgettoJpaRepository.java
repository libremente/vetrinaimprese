package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTTipologiaProgettoEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTTipologiaProgettoJpaRepository extends PagingAndSortingRepository<PLFTTipologiaProgettoEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_TIPOLOGIA_PROGETTO a where a.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTTipologiaProgettoEntity> findTipologiaProgetto();
	
	@Query(value = "SELECT a from PLFTTipologiaProgettoEntity a where a.codice = :codice")
	public abstract List<PLFTTipologiaProgettoEntity> findTipologiaProgettoByCodice(@Param("codice") String codice);

}
