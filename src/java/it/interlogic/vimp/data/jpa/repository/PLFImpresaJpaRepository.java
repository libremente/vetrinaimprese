package it.interlogic.vimp.data.jpa.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import it.interlogic.vimp.data.jpa.model.PLFImpresaEntity;

public interface PLFImpresaJpaRepository extends PagingAndSortingRepository<PLFImpresaEntity, BigDecimal>
{
	
	@Query("SELECT COUNT(t) FROM PLFImpresaEntity t where t.plfTTipoImpresa.id <> 2 and t.plfTStatoImpresa.id = :statoImpresa and t.dataCancellazione is null and t.pubblicato = 1")
	long countAll(@Param("statoImpresa") BigDecimal statoImpresa);

	@Query("SELECT COUNT(t) FROM PLFImpresaEntity t where t.plfTTipoImpresa.id <> 2 and (t.plfTStatoImpresa.id = :statoImpresa or t.plfTStatoImpresa.id = :statoImpresaBis) and t.dataCancellazione is null and t.pubblicato = 1")
	long countAll(@Param("statoImpresa") BigDecimal statoImpresa, @Param("statoImpresaBis") BigDecimal statoImpresaBis);
	
	
	@Query("SELECT COUNT(t) FROM PLFImpresaEntity t where t.plfTTipoImpresa.id <> 2 and t.plfTOrigineImpresa in (1,2) and t.dataCancellazione is null and t.pubblicato = 1")
	long countSpinoff();
	
	

	@Query("SELECT i FROM PLFImpresaEntity i WHERE i.plfTTipoImpresa.id = 2 AND i.dataCancellazione is null")
	List<PLFImpresaEntity> findStakeholder();
	
	@Query("SELECT i FROM PLFImpresaEntity i WHERE i.plfTTipoImpresa.id <> 2 AND i.dataCancellazione is null")
	List<PLFImpresaEntity> findImprese();

	@Query(value = "SELECT p from PLFImpresaEntity p where p.impresaTranslation.descImpresa =:nome ")
	public List<PLFImpresaEntity> findByDescImpresa(@Param("nome") String nome);

	@Query(value = "SELECT * FROM PLF_IMPRESA PR,PLF_R_SERVIZI_IMPRESA RPR WHERE PR.ID_PLF_IMPRESA = RPR.ID_PLF_IMPRESA AND RPR.ID_SERVIZI = :idServizi", nativeQuery = true)
	List<PLFImpresaEntity> findByIdServizi(@Param("idServizi") BigDecimal idServizi);

	
	@Query(value = "SELECT * FROM PLF_IMPRESA PR,PLF_R_IMPRESA_STAKEHOLDER RPR WHERE PR.ID_PLF_IMPRESA = RPR.ID_PLF_IMPRESA AND RPR.ID_STAKEHOLDER = :idStakeholderParam", nativeQuery = true)
	List<PLFImpresaEntity> findImpreseStakeholderCollegate(@Param("idStakeholderParam") BigDecimal idStakeholder);
	
	@Query(value = "SELECT * FROM PLF_IMPRESA STAKE,PLF_R_IMPRESA_STAKEHOLDER RPR WHERE STAKE.ID_PLF_IMPRESA = RPR.ID_STAKEHOLDER AND RPR.ID_PLF_IMPRESA = :idImpresa", nativeQuery = true)
	public abstract List<PLFImpresaEntity> findStakeholderByIdImpresa(@Param("idImpresa") BigDecimal idImpresa);
	
	
	@Query(value = "SELECT * FROM PLF_IMPRESA STAKE,PLF_R_IMPRESA_STAKEHOLDER RPR WHERE STAKE.ID_PLF_IMPRESA = RPR.ID_PLF_IMPRESA AND RPR.ID_STAKEHOLDER = :idStakeholder", nativeQuery = true)
	public abstract List<PLFImpresaEntity> findImpreseByIdStakeholder(@Param("idStakeholder") BigDecimal idStakeholder);
	
	
	@Query(value = "SELECT p from PLFImpresaEntity p where p.partitaIva =:partitaIva ")
	public List<PLFImpresaEntity> findByPartitaIva(@Param("partitaIva") String partitaIva);
	
	@Query(value = "SELECT p from PLFImpresaEntity p where p.codFiscale =:codiceFiscale ")
	public List<PLFImpresaEntity> findByCodiceFiscale(@Param("codiceFiscale") String codiceFiscale);

	@Query(value = "SELECT I.* FROM PLF_IMPRESA I JOIN PLF_V_DELEGATO D ON D.ID_PLF_IMPRESA = I.ID_PLF_IMPRESA WHERE D.ID_UTENTE = :idUtente", nativeQuery = true)
    List<PLFImpresaEntity> findImpreseDelegato(@Param("idUtente") BigDecimal idUtente);
}
