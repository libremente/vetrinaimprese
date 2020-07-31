package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFNewsImpresaEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFNewsImpresaJpaRepository extends PagingAndSortingRepository<PLFNewsImpresaEntity, BigDecimal>
{
	
	@Query(value = "SELECT p from PLFNewsImpresaEntity p where p.plfProgettiProdotti.idPlfProgettiProdotti =:idProgetto ")
	public List<PLFNewsImpresaEntity> findNewsByIdProgetto(@Param("idProgetto") BigDecimal idProgetto);
	
	@Query(value = "SELECT p from PLFNewsImpresaEntity p where p.plfServizi.idServizi =:idServizio ")
	public List<PLFNewsImpresaEntity> findNewsByIdServizio(@Param("idServizio") BigDecimal idServizio);
	

	@Query(value = "SELECT p from PLFNewsImpresaEntity p where p.newsImpresaTranslation.descrizione =:nome ")
	public List<PLFNewsImpresaEntity> findNewsImpresaByDescrizione(@Param("nome") String nome);

	@Query(value = "SELECT * from PLF_NEWS_IMPRESA NEWS where NEWS.ID_PLF_IMPRESA =:id ", nativeQuery = true)
	public List<PLFNewsImpresaEntity> findNewsImpresaByImpresa(@Param("id") BigDecimal id);
	
	
	@Query(value = "SELECT p from PLFNewsImpresaEntity p where p.plfImpresa.idPlfImpresa =:id and p.pubblicato = 1 and (p.dataFine is null or p.dataFine> " + SQLAdapterUtil.DATE_NOW + " )")
	public List<PLFNewsImpresaEntity> findNewsImpresaByImpresaAttive(@Param("id") BigDecimal id);
	

	@Query(value = "SELECT DISTINCT n.* from PLF_NEWS_IMPRESA n " +
			"LEFT JOIN PLF_PROGETTI_PRODOTTI pp ON pp.ID_PLF_IMPRESA = n.ID_PLF_IMPRESA or pp.ID_PLF_PROGETTI_PRODOTTI = n.ID_PLF_PROGETTI_PRODOTTI " +
			"where pp.ID_PLF_IMPRESA =:id OR n.ID_PLF_IMPRESA = :id ", nativeQuery = true)
	public List<PLFNewsImpresaEntity> findNewsImpresaByProgettoImpresa(@Param("id") BigDecimal id);


	@Query(value = "SELECT DISTINCT n.* from PLF_NEWS_IMPRESA n " +
			"LEFT JOIN PLF_R_SERVIZI_IMPRESA ris ON ris.ID_PLF_IMPRESA = n.ID_PLF_IMPRESA or ris.ID_SERVIZI = n.ID_SERVIZI_IMPRESA " +
			"where ris.ID_PLF_IMPRESA =:id OR n.ID_PLF_IMPRESA = :id ", nativeQuery = true)
	public List<PLFNewsImpresaEntity> findNewsImpresaByServizi(@Param("id") BigDecimal id);
	

}
