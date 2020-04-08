package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTTipoAllegatoEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTTipoAllegatoJpaRepository extends PagingAndSortingRepository<PLFTTipoAllegatoEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_TIPO_ALLEGATO a where a.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTTipoAllegatoEntity> findTipoAllegato();
	
	@Query(value = "SELECT a from PLFTTipoAllegatoEntity a where a.codice = :codice")
	public abstract List<PLFTTipoAllegatoEntity> findTipoAllegatoByCodice(@Param("codice") String codice);

}
