package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTProvinciaEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTProvinciaJpaRepository extends PagingAndSortingRepository<PLFTProvinciaEntity, BigDecimal>
{
	@Query("SELECT i FROM PLFTProvinciaEntity i WHERE i.regione.idRegione = :regione AND i.dataFineProvincia is null")
	public abstract List<PLFTProvinciaEntity> findProvincia(@Param("regione") BigDecimal regione);
	
	
	@Query("SELECT i FROM PLFTProvinciaEntity i WHERE i.regione.codRegione = :codRegione AND i.dataFineProvincia is null order by i.descProvincia")
	public abstract List<PLFTProvinciaEntity> findProvincia(@Param("codRegione") String codRegione);
}
