package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTSettoreImpresaEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTSettoreImpresaJpaRepository extends PagingAndSortingRepository<PLFTSettoreImpresaEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_SETTORE_IMPRESA tipo where tipo.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTSettoreImpresaEntity> findSettoreImpresa();

	@Query(value = "SELECT tipo from PLFTSettoreImpresaEntity tipo where tipo.codice = :codice")
	public abstract List<PLFTSettoreImpresaEntity> findSettoreImpresaPerCodice(@Param("codice") String codice);

	@Query(value = "SELECT tipo from PLFTSettoreImpresaEntity tipo where UPPER(tipo.descrizione) = :descrizione")
	public abstract List<PLFTSettoreImpresaEntity> findSettoreImpresaPerDescrizione(@Param("descrizione") String descrizione);

}
