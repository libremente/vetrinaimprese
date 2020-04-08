package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTNazioneEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTNazioneJpaRepository extends PagingAndSortingRepository<PLFTNazioneEntity, BigDecimal>
{
	@Query(value = "SELECT * FROM PLF_T_NAZIONE NAZIONE WHERE NAZIONE.COD_NAZIONE = :codiceNazione", nativeQuery = true)
	public abstract List<PLFTNazioneEntity> findByCodiceNazione(@Param("codiceNazione") String codiceNazione);
	
}
