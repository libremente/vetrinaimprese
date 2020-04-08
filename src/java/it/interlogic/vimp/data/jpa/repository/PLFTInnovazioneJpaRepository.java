package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTInnovazioneEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTInnovazioneJpaRepository extends PagingAndSortingRepository<PLFTInnovazioneEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_INNOVAZIONE tipo where tipo.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTInnovazioneEntity> findInnovazione();

	@Query(value = "SELECT tipo from PLFTInnovazioneEntity tipo where tipo.codice = :codice")
	public abstract List<PLFTInnovazioneEntity> findInnovazionePerCodice(@Param("codice") String codice);
	
	
	@Query(value = "SELECT * FROM PLF_T_INNOVAZIONE PR,PLF_R_IMPRESA_INNOVAZIONE RPR WHERE PR.ID = RPR.ID_INNOVAZIONE AND RPR.ID_PLF_IMPRESA = :idImpresa", nativeQuery = true)
	public abstract List<PLFTInnovazioneEntity> findByIdImpresa(@Param("idImpresa") BigDecimal idImpresa);

}
