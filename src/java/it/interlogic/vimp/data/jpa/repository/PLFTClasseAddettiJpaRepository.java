package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTClasseAddettiEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTClasseAddettiJpaRepository extends PagingAndSortingRepository<PLFTClasseAddettiEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_CLASSE_ADDETTI tipo where tipo.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTClasseAddettiEntity> findClasseAddetti();

	@Query(value = "SELECT tipo from PLFTClasseAddettiEntity tipo where UPPER(tipo.codice) = :codice")
	public abstract List<PLFTClasseAddettiEntity> findClasseAddettiPerCodice(@Param("codice") String codice);

}
