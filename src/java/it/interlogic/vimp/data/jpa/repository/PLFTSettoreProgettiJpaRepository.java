package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTSettoreProgettiEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTSettoreProgettiJpaRepository extends PagingAndSortingRepository<PLFTSettoreProgettiEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_SETTORE_PROGETTI tipo where tipo.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTSettoreProgettiEntity> findSettoreProgetti();

	@Query(value = "SELECT tipo from PLFTSettoreProgettiEntity tipo where tipo.codice = :codice")
	public abstract List<PLFTSettoreProgettiEntity> findSettoreProgettiPerCodice(@Param("codice") String codice);

}
