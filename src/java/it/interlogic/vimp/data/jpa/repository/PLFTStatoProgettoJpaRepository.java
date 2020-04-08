package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTStatoProgettoEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTStatoProgettoJpaRepository extends PagingAndSortingRepository<PLFTStatoProgettoEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_STATO_PROGETTO tipo where tipo.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTStatoProgettoEntity> findStatoProgetto();
	
	@Query(value = "SELECT tipo from PLFTStatoProgettoEntity tipo where tipo.codice = :codice")
	public abstract List<PLFTStatoProgettoEntity> findStatoProgettoByCodice(@Param("codice") String codice);

}
