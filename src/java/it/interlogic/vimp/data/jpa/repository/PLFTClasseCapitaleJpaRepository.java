package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTClasseCapitaleEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTClasseCapitaleJpaRepository extends PagingAndSortingRepository<PLFTClasseCapitaleEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_CLASSE_CAPITALE tipo where tipo.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTClasseCapitaleEntity> findClasseCapitale();

	@Query(value = "SELECT tipo from PLFTClasseCapitaleEntity tipo where UPPER(tipo.codice) = :codice")
	public abstract List<PLFTClasseCapitaleEntity> findClasseCapitalePerCodice(@Param("codice") String codice);

}
