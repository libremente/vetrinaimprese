package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTTipoProgrammaEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTTipoProgrammaJpaRepository extends PagingAndSortingRepository<PLFTTipoProgrammaEntity, BigDecimal>
{

	//@Query(value = "SELECT * from PLF_T_TIPO_PROGRAMMA tipo where tipo.DATA_FINE_TIPO_PROGRAMMA IS NULL", nativeQuery = true)
	
	@Query(value = "SELECT tipo from PLFTTipoProgrammaEntity tipo where tipo.dataFine is null")
	public abstract List<PLFTTipoProgrammaEntity> findTipoProgramma();

	@Query(value = "SELECT tipo from PLFTTipoProgrammaEntity tipo where tipo.codice = :codice")
	public abstract List<PLFTTipoProgrammaEntity> findTipoProgrammaByCodice(@Param("codice") String codice);

}
