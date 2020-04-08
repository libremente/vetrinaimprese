package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFRichiestaAccreditamentoEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PLFRichiestaAccreditamentoJpaRepository extends PagingAndSortingRepository<PLFRichiestaAccreditamentoEntity, BigDecimal>
{
	@Query(value = "SELECT p from PLFRichiestaAccreditamentoEntity p where p.partitaIva =:partitaIva ")
	public List<PLFRichiestaAccreditamentoEntity> findByPartitaIva(@Param("partitaIva") String partitaIva);

	@Query(value = "SELECT p from PLFRichiestaAccreditamentoEntity p where p.codFiscale =:codiceFiscale ")
	public List<PLFRichiestaAccreditamentoEntity> findByCodiceFiscale(@Param("codiceFiscale") String codiceFiscale);
}
