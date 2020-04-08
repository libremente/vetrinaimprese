package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFOpportunitaEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFOpportunitaJpaRepository extends PagingAndSortingRepository<PLFOpportunitaEntity, BigDecimal>
{
	
	@Query(value = "SELECT p from PLFOpportunitaEntity p where p.opportunitaTranslation.descNome =:nome ")
	public List<PLFOpportunitaEntity> findOpportunitaByDescrizione(@Param("nome") String nome);
	
	
	
	
}
