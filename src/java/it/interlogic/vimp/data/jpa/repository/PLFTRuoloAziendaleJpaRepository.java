package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTRuoloAziendaleEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTRuoloAziendaleJpaRepository extends PagingAndSortingRepository<PLFTRuoloAziendaleEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_RUOLO_AZIENDALE tipo where tipo.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTRuoloAziendaleEntity> findRuoloAziendale();

	@Query(value = "SELECT tipo from PLFTRuoloAziendaleEntity tipo where tipo.codice = :codice")
	public abstract List<PLFTRuoloAziendaleEntity> findRuoloAziendalePerCodice(@Param("codice") String codice);

}
