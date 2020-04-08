package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTRuoloEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PLFTRuoloJpaRepository extends PagingAndSortingRepository<PLFTRuoloEntity, BigDecimal>
{	
	@Query(value = "SELECT r from PLFTRuoloEntity r where r.dataCancellazione is null")
	public abstract List<PLFTRuoloEntity> findRuoli();
	
	@Modifying
	@Query("UPDATE PLFTRuoloEntity r set r.descRuolo = :descrizione where r.idRuolo = :id")
	public abstract int updateRuolo(@Param("id") BigDecimal id, @Param("descrizione") String descrizione);
}
