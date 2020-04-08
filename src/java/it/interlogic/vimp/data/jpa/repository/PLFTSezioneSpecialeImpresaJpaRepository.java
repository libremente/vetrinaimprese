package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTSezioneSpecialeImpresaEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTSezioneSpecialeImpresaJpaRepository extends PagingAndSortingRepository<PLFTSezioneSpecialeImpresaEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_SEZIONE_SPECIALE_IMPRESA a where a.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTSezioneSpecialeImpresaEntity> findSezioneSpecialeImpresa();
	
	@Query(value = "SELECT a from PLFTSezioneSpecialeImpresaEntity a where a.codice = :codice")
	public abstract List<PLFTSezioneSpecialeImpresaEntity> findSezioneSpecialeImpresaByCodice(@Param("codice") String codice);

}
