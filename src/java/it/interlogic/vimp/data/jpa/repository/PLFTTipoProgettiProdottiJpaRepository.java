package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTTipoProgettiProdottiEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTTipoProgettiProdottiJpaRepository extends PagingAndSortingRepository<PLFTTipoProgettiProdottiEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_TIPO_PROGETTI_PRODOTTI a where a.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTTipoProgettiProdottiEntity> findTipoProgettiProdotti();
	
	@Query(value = "SELECT a from PLFTTipoProgettiProdottiEntity a where a.codice = :codice")
	public abstract List<PLFTTipoProgettiProdottiEntity> findTipoProgettiProdottiByCodice(@Param("codice") String codice);

}
