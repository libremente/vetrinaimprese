package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTTipoProgettoEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTTipoProgettoJpaRepository extends PagingAndSortingRepository<PLFTTipoProgettoEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_TIPO_PROGETTO tipo where tipo.DATA_FINE_TIPO_PROGETTO IS NULL", nativeQuery = true)
	public abstract List<PLFTTipoProgettoEntity> findTipoProgetto();
	
	@Query(value = "SELECT tipo from PLFTTipoProgettoEntity tipo where tipo.codice = :codProgetto")
	public abstract List<PLFTTipoProgettoEntity> findTipoProgettoByCodProgetto(@Param("codProgetto") String codPrrogetto);

}
