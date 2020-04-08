package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTTipoOrganizzazioneEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface PLFTTipoOrganizzazioneJpaRepository extends PagingAndSortingRepository<PLFTTipoOrganizzazioneEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_TIPO_ORGANIZZAZIONE tipo where tipo.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTTipoOrganizzazioneEntity> findTipoOrganizzazione();
	
	@Query(value = "SELECT tipo from PLFTTipoOrganizzazioneEntity tipo where tipo.codice = :codice")
	public abstract List<PLFTTipoOrganizzazioneEntity> findTipoOrganizzazionePerCodice(@Param("codice") String codice);

}
