package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTTipoNewsEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTTipoNewsJpaRepository extends PagingAndSortingRepository<PLFTTipoNewsEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_TIPO_NEWS tipo where tipo.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTTipoNewsEntity> findTipoNews();

	@Query(value = "SELECT tipo from PLFTTipoNewsEntity tipo where tipo.codice = :codice")
	public abstract List<PLFTTipoNewsEntity> findTipoNewsPerCodice(@Param("codice") String codice);

}
