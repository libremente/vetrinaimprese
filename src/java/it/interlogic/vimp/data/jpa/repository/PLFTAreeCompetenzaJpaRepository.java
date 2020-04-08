package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTAreeCompetenzaEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTAreeCompetenzaJpaRepository extends PagingAndSortingRepository<PLFTAreeCompetenzaEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_AREE_COMPETENZA tipo where tipo.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTAreeCompetenzaEntity> findAreeCompetenza();

	@Query(value = "SELECT tipo from PLFTAreeCompetenzaEntity tipo where tipo.codice = :codice")
	public abstract List<PLFTAreeCompetenzaEntity> findAreeCompetenzaPerCodice(@Param("codice") String codice);
	
	@Query(value = "SELECT tipo from PLFTAreeCompetenzaEntity tipo where tipo.descrizione = :descrizione")
	public abstract List<PLFTAreeCompetenzaEntity> findAreeCompetenzaPerDescrizione(@Param("descrizione") String descrizione);
	

}
