package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTAtecoEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTAtecoJpaRepository extends PagingAndSortingRepository<PLFTAtecoEntity, BigDecimal>
{

	@Query(value = "SELECT * from PLF_T_ATECO ", nativeQuery = true)
	public abstract List<PLFTAtecoEntity> findAteco();

	@Query(value = "SELECT tipo from PLFTAtecoEntity tipo where tipo.attivita = :attivita")
	public abstract List<PLFTAtecoEntity> findAtecoPerAttivita(@Param("attivita") BigDecimal attivita);

	@Query(value = "SELECT tipo from PLFTAtecoEntity tipo where tipo.codifica = :codifica")
	public abstract List<PLFTAtecoEntity> getAtecoPerCodifica(@Param("codifica") BigDecimal codifica);

}
