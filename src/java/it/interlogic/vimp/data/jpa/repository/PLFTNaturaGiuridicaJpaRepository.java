package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTNaturaGiuridicaEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTNaturaGiuridicaJpaRepository extends PagingAndSortingRepository<PLFTNaturaGiuridicaEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_NATURA_GIURIDICA tipo where tipo.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTNaturaGiuridicaEntity> findNaturaGiuridica();

	@Query(value = "SELECT tipo from PLFTNaturaGiuridicaEntity tipo where tipo.codice = :codice")
	public abstract List<PLFTNaturaGiuridicaEntity> findNaturaGiuridicaPerCodice(@Param("codice") String codice);
	
	@Query(value = "SELECT tipo from PLFTNaturaGiuridicaEntity tipo where UPPER(tipo.descrizione) = :descrizione")
	public abstract List<PLFTNaturaGiuridicaEntity> findNaturaGiuridicaPerDescrizione(@Param("descrizione") String descrizione);

}
