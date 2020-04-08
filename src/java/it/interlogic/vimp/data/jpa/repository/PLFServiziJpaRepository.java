package it.interlogic.vimp.data.jpa.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import it.interlogic.vimp.data.jpa.model.PLFServiziEntity;

public interface PLFServiziJpaRepository extends PagingAndSortingRepository<PLFServiziEntity, BigDecimal>
{
	
	@Query(value = "SELECT p from PLFServiziEntity p where p.dataCancellazione is null and (p.dataFine is null or p.dataFine > " + SQLAdapterUtil.DATE_NOW +  ") and p.pubblicato = 1")
	List<PLFServiziEntity> findAllAttivi();

	@Query(value = "SELECT p from PLFServiziEntity p where p.serviziTranslation.descrizione =:nome ")
	List<PLFServiziEntity> findByDescrizione(@Param("nome") String nome);

	@Query(value = "SELECT p from PLFServiziEntity p where p.serviziTranslation.titolo =:nome ")
	List<PLFServiziEntity> findByTitolo(@Param("nome") String titolo);
	
	@Query(value = "SELECT * FROM PLF_SERVIZI PR,PLF_R_SERVIZI_IMPRESA RPR WHERE PR.DATA_CANCELLAZIONE IS NULL AND PR.ID_SERVIZI = RPR.ID_SERVIZI AND RPR.ID_PLF_IMPRESA = :idImpresa AND (PR.DATA_FINE IS NULL OR PR.DATA_FINE > " + SQLAdapterUtil.DATE_NOW +  ")", nativeQuery = true)
	List<PLFServiziEntity> findByIdImpresa(@Param("idImpresa") BigDecimal idImpresa);
	
	
	@Query(value = "SELECT * FROM PLF_SERVIZI PR,PLF_R_SERVIZI_IMPRESA RPR WHERE PR.DATA_CANCELLAZIONE IS NULL AND PR.ID_SERVIZI = RPR.ID_SERVIZI AND RPR.ID_PLF_IMPRESA = :idImpresa AND (PR.DATA_FINE IS NULL OR PR.DATA_FINE > " + SQLAdapterUtil.DATE_NOW +  ") AND PR.PUBBLICATO = 1", nativeQuery = true)
	List<PLFServiziEntity> findByIdImpresaPubblicati(@Param("idImpresa") BigDecimal idImpresa);
	
	
	@Query(value = "SELECT * FROM PLF_SERVIZI PR,PLF_R_SERVIZI_IMPRESA RPR WHERE PR.ID_SERVIZI = RPR.ID_SERVIZI AND RPR.ID_PLF_IMPRESA = :idImpresa AND PR.DATA_CANCELLAZIONE IS NULL ", nativeQuery = true)
	List<PLFServiziEntity> findAllByImpresa(@Param("idImpresa") BigDecimal idImpresa);

	@Query(value = "SELECT S.* FROM PLF_SERVIZI S " +
			"JOIN PLF_R_SERVIZI_IMPRESA SI ON SI.ID_SERVIZI = S.ID_SERVIZI " +
			"JOIN PLF_V_DELEGATO D ON D.ID_PLF_IMPRESA = SI.ID_PLF_IMPRESA " +
			"WHERE S.DATA_CANCELLAZIONE IS NULL AND D.ID_UTENTE = :idUtente", nativeQuery = true)
    List<PLFServiziEntity> findByDelegato(@Param("idUtente") BigDecimal idUtente);

	@Query(value = "SELECT TM.ID_SERVIZI FROM PLF_R_PACCHETTO_SERVIZI TM WHERE TM.ID_PACCHETTO_SERVIZI = :idPacchettoServizi", nativeQuery = true)
    List<BigDecimal> findIdsByPacchetto(@Param("idPacchettoServizi") BigDecimal idPacchettoServizi);

	@Query(value="SELECT S.* FROM PLF_SERVIZI S JOIN PLF_R_PACCHETTO_SERVIZI RPS ON S.ID_SERVIZI = RPS.ID_SERVIZI WHERE S.DATA_CANCELLAZIONE IS NULL AND RPS.ID_PACCHETTO_SERVIZI = :idPacchettoServizi", nativeQuery = true)
	List<PLFServiziEntity> findByPacchetto(@Param("idPacchettoServizi") BigDecimal idPacchettoServizi);
	
	
	@Query("SELECT COUNT(t) FROM PLFServiziEntity t where t.denominazioneServizio.id = :idDenominazioneServizio and t.serviziStandard = 'S' and t.dataCancellazione is null ")
	long countServiziByDenominazione(@Param("idDenominazioneServizio") BigDecimal idDenominazioneServizio);
	
	
	
}
