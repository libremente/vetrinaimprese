package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTTipoServizioEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTTipoServizioJpaRepository extends PagingAndSortingRepository<PLFTTipoServizioEntity, BigDecimal>
{	
	@Query(value = "SELECT tipo from PLFTTipoServizioEntity tipo where tipo.dataFine is null")
	public abstract List<PLFTTipoServizioEntity> findTipoServizio();
	
	
	@Query(value = "SELECT tipo from PLFTTipoServizioEntity tipo where tipo.descrizione = :descrizione")
	public abstract List<PLFTTipoServizioEntity> findTipoServizioPerDescrizione(@Param("descrizione") String descrizione);

}
