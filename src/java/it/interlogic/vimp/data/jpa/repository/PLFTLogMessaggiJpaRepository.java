package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTLogMessaggiEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTLogMessaggiJpaRepository extends PagingAndSortingRepository<PLFTLogMessaggiEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_LOG_MESSAGGI a where a.DATA_FINE IS NULL", nativeQuery = true)
	public abstract List<PLFTLogMessaggiEntity> findLogMessaggi();
	
	@Query(value = "SELECT a from PLFTLogMessaggiEntity a where a.codice = :codice")
	public abstract List<PLFTLogMessaggiEntity> findLogMessaggiByCodice(@Param("codice") String codice);

}
