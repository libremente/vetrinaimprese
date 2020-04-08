package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTModalitaErogazioneServizioEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTModalitaErogazioneServizioJpaRepository extends PagingAndSortingRepository<PLFTModalitaErogazioneServizioEntity, BigDecimal> {

	@Query(value = "SELECT m FROM PLFTModalitaErogazioneServizioEntity m WHERE dataFine IS NULL")
	List<PLFTModalitaErogazioneServizioEntity> findModalitaErogazioneServizio();
	
	
	@Query(value = "SELECT tipo from PLFTModalitaErogazioneServizioEntity tipo where tipo.descrizione = :descrizione")
	public abstract List<PLFTModalitaErogazioneServizioEntity> findModalitaErogazioneServizioPerDescrizione(@Param("descrizione") String descrizione);
	
}
