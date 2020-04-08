package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTUtenteEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTUtenteJpaRepository extends PagingAndSortingRepository<PLFTUtenteEntity, BigDecimal>
{
	@Query("SELECT COUNT(t) FROM PLFTUtenteEntity t where t.dataCancellazione is null")
	long countAll();

	@Query("SELECT u FROM PLFTUtenteEntity u where u.email = :emailParam")
	PLFTUtenteEntity findUtenteByEmail(@Param("emailParam") String emailParam);

	@Query("SELECT u FROM PLFTUtenteEntity u where u.email = :emailParam and u.dataCancellazione is null")
	PLFTUtenteEntity findUtenteByEmailValido(@Param("emailParam") String emailParam);

	@Query("SELECT u FROM PLFTUtenteEntity u where u.codiceFiscale = :codiceFiscaleParam")
	PLFTUtenteEntity findUtenteByCodiceFiscale(@Param("codiceFiscaleParam") String codiceFiscaleParam);

	@Query("SELECT u FROM PLFTUtenteEntity u where u.codiceFiscale = :codiceFiscaleParam and u.dataCancellazione is null")
	PLFTUtenteEntity findUtenteByCodiceFiscaleValido(@Param("codiceFiscaleParam") String codiceFiscaleParam);

	@Query(value = "SELECT PR.id_PLF_IMPRESA, PRT.DESC_IMPRESA, PR.ID_TIPO_IMPRESA FROM PLF_IMPRESA PR, PLF_IMPRESA_TRANSLATION PRT,PLF_R_UTENTE_IMPRESA RPR "
			+ "WHERE PR.ID_PLF_IMPRESA = RPR.ID_PLF_IMPRESA AND PRT.ID_PLF_IMPRESA = PR.ID_PLF_IMPRESA AND RPR.ID_UTENTE = :idUtenteParam", nativeQuery = true)
	List<Object[]> findImpresa(@Param("idUtenteParam") BigDecimal idUtente);
	
	
	@Query("SELECT u FROM PLFTUtenteEntity u where u.dataCancellazione is null")
	List<PLFTUtenteEntity> findAllUtenti();
}
