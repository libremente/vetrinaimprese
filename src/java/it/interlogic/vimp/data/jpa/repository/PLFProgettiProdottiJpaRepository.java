package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFProgettiProdottiEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFProgettiProdottiJpaRepository extends PagingAndSortingRepository<PLFProgettiProdottiEntity, BigDecimal>
{
	

	@Query("SELECT COUNT(t) FROM PLFProgettiProdottiEntity t where t.plfTTipoProgettiProdotti.id = :tipologia and t.dataCancellazione is null and t.pubblicato = 1")
	long countAll(@Param("tipologia") BigDecimal tipologia);
	
	
	@Query("SELECT COUNT(t) FROM PLFProgettiProdottiEntity t where " +
			"(t.plfTTipoProgettiProdotti.id = 2 or t.plfTTipoProgettiProdotti = 3) " +
			"and t.dataCancellazione is null and t.pubblicato = 1")
	long countProdottiTecnologie();

	
	
	@Query(value = "SELECT p.* from PLF_PROGETTI_PRODOTTI p " +
			"JOIN PLF_PROGETTI_PRODOTTI_TRANSLATION pt ON p.ID_PLF_PROGETTI_PRODOTTI = pt.ID_PLF_PROGETTI_PRODOTTI " +
			"where NOME_PROGETTO_PRODOTTO = :nome ", nativeQuery = true)
	public List<PLFProgettiProdottiEntity> findProgettiByNome(@Param("nome") String nome);
	
	
	@Query(value = "SELECT p from PLFProgettiProdottiEntity p where p.plfImpresa.idPlfImpresa =:idImpresa ")
	public List<PLFProgettiProdottiEntity> findProgettiByIdImpresa(@Param("idImpresa") BigDecimal idImpresa);

	@Query(value = "SELECT p from PLFProgettiProdottiEntity p where p.idPlfProgettiProdotti =:idProgettoProdotto ")
	public List<PLFProgettiProdottiEntity> findProgettiByIdProgettoProdotto(@Param("idProgettoProdotto") BigDecimal idProgettoProdotto);
	
	
	@Query(value = "SELECT p from PLFProgettiProdottiEntity p where p.plfImpresa.idPlfImpresa =:idImpresa and p.pubblicato = 1 and p.dataCancellazione is null")
	public List<PLFProgettiProdottiEntity> findProgettiByIdImpresaAttivi(@Param("idImpresa") BigDecimal idImpresa);
	
	
	@Query(value = "SELECT p from PLFProgettiProdottiEntity p where p.plfImpresa.idPlfImpresa =:idImpresa and p.pubblicato = 1 and p.dataCancellazione is null and (p.dataScadenza is null or p.dataScadenza > " + SQLAdapterUtil.DATE_NOW + " )")
	public List<PLFProgettiProdottiEntity> findProgettiByIdImpresaAttiviNonScaduti(@Param("idImpresa") BigDecimal idImpresa);

}
