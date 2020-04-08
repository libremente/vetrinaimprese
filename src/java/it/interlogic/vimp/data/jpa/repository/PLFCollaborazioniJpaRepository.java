package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFCollaborazioniEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFCollaborazioniJpaRepository extends PagingAndSortingRepository<PLFCollaborazioniEntity, BigDecimal>
{

	@Query(value = "SELECT p from PLFCollaborazioniEntity p where p.plfImpresa.idPlfImpresa =:idImpresa ")
	public List<PLFCollaborazioniEntity> findCollaborazioniByIdImpresa(@Param("idImpresa") BigDecimal idImpresa);

	@Query(value = "SELECT p from PLFCollaborazioniEntity p where p.plfProgettiProdotti.idPlfProgettiProdotti =:idProgetto ")
	public List<PLFCollaborazioniEntity> findCollaborazioniByIdProgetto(@Param("idProgetto") BigDecimal idProgetto);
	
	@Query(value = "SELECT p from PLFCollaborazioniEntity p where p.plfImpresa.idPlfImpresa =:idImpresa and  p.plfProgettiProdotti.idPlfProgettiProdotti =:idProgetto ")
	public List<PLFCollaborazioniEntity> findByImpresaProdotto(@Param("idImpresa") BigDecimal idImpresa,@Param("idProgetto") BigDecimal idProgetto);
	
	@Query(value = "SELECT p from PLFCollaborazioniEntity p where p.partitaIva =:partitaIva and p.plfProgettiProdotti.idPlfProgettiProdotti =:idProgetto ")
	public List<PLFCollaborazioniEntity> findByPartitaIvaProdotto(@Param("partitaIva") String partitaIva,@Param("idProgetto") BigDecimal idProgetto);
	
	@Query(value = "SELECT p from PLFCollaborazioniEntity p where p.codiceFiscale =:codiceFiscale and p.plfProgettiProdotti.idPlfProgettiProdotti =:idProgetto ")
	public List<PLFCollaborazioniEntity> findByCodiceFiscaleProdotto(@Param("codiceFiscale") String codiceFiscale,@Param("idProgetto") BigDecimal idProgetto);
	
	



}
