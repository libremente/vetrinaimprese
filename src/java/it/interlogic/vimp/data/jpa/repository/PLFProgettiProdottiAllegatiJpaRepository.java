package it.interlogic.vimp.data.jpa.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import it.interlogic.vimp.data.jpa.model.PLFProgettiProdottiAllegatiEntity;

public interface PLFProgettiProdottiAllegatiJpaRepository extends PagingAndSortingRepository<PLFProgettiProdottiAllegatiEntity, BigDecimal>
{
	@Query(value = "SELECT p from PLFProgettiProdottiAllegatiEntity p where p.idPlfProgettiProdotti =:idProgettoProdotto ")
	public List<PLFProgettiProdottiAllegatiEntity> findByProgettoProdotto(@Param("idProgettoProdotto") BigDecimal idProgettoProdotto);
}
