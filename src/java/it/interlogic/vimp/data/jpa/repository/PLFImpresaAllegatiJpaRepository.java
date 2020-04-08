package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFImpresaAllegatiEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFImpresaAllegatiJpaRepository extends PagingAndSortingRepository<PLFImpresaAllegatiEntity, BigDecimal>
{
	@Query(value = "SELECT p from PLFImpresaAllegatiEntity p where p.idPlfImpresa =:idImpresa ")
	public List<PLFImpresaAllegatiEntity> findByImpresa(@Param("idImpresa") BigDecimal idImpresa);
	

}
