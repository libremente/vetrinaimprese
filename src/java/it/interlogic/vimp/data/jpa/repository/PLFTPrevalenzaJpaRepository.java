package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTPrevalenzaEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTPrevalenzaJpaRepository extends PagingAndSortingRepository<PLFTPrevalenzaEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_PREVALENZA tipo where tipo.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTPrevalenzaEntity> findPrevalenza();

	@Query(value = "SELECT tipo from PLFTPrevalenzaEntity tipo where UPPER(tipo.codice) = :codice")
	public abstract List<PLFTPrevalenzaEntity> findPrevalenzaPerCodice(@Param("codice") String codice);

}
