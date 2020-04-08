package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTComuneEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTComuneJpaRepository extends PagingAndSortingRepository<PLFTComuneEntity, BigDecimal>
{

	@Query("SELECT i FROM PLFTComuneEntity i WHERE i.provincia.idProvincia = :provincia AND i.dataFineComune is null")	
	public abstract List<PLFTComuneEntity> findComune(@Param("provincia") BigDecimal provincia);
	
	
	@Query("SELECT i FROM PLFTComuneEntity i WHERE i.provincia.codProvincia = :provincia AND i.dataFineComune is null order by i.descComune")	
	public abstract List<PLFTComuneEntity> findComune(@Param("provincia") String provincia);
	
	@Query("SELECT i FROM PLFTComuneEntity i WHERE i.provincia.idProvincia is null AND i.dataFineComune is null")	
	public abstract List<PLFTComuneEntity> findComuneStatiEsteri();
	
	
	@Query("SELECT i FROM PLFTComuneEntity i WHERE UPPER(i.descComune) = :nome")	
	public abstract List<PLFTComuneEntity> findComuneByNome(@Param("nome") String nome);
	
	@Query("SELECT i FROM PLFTComuneEntity i WHERE UPPER(i.codiceIstat) = :codice")	
	public abstract List<PLFTComuneEntity> findComuneByCodice(@Param("codice") String codice);
	
}
