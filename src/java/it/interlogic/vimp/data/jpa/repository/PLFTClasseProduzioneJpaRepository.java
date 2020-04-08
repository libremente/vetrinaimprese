package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTClasseProduzioneEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTClasseProduzioneJpaRepository extends PagingAndSortingRepository<PLFTClasseProduzioneEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_CLASSE_PRODUZIONE tipo where tipo.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTClasseProduzioneEntity> findClasseProduzione();

	@Query(value = "SELECT tipo from PLFTClasseProduzioneEntity tipo where UPPER(tipo.codice) = :codice")
	public abstract List<PLFTClasseProduzioneEntity> findClasseProduzionePerCodice(@Param("codice") String codice);

}
