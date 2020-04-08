package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTSoggAmmissibiliEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface PLFTSoggAmmissibiliJpaRepository extends PagingAndSortingRepository<PLFTSoggAmmissibiliEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_SOGG_AMMISSIBILI tipo where tipo.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTSoggAmmissibiliEntity> findSoggAmmissibili();
	
	@Query(value = "SELECT tipo from PLFTSoggAmmissibiliEntity tipo where tipo.codice = :codice")
	public abstract List<PLFTSoggAmmissibiliEntity> findSoggAmmissibiliByCodice(@Param("codice") String codice);

}
