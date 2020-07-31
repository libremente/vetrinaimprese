package it.interlogic.vimp.data.jpa.repository.relation;

import it.interlogic.vimp.data.jpa.model.relation.PLFRUtenteImpresaEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRUtenteImpresaEntityKey;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFRUtenteImpresaJpaRepository extends PagingAndSortingRepository<PLFRUtenteImpresaEntity, PLFRUtenteImpresaEntityKey>
{
	@Query(value = "SELECT p from PLFRUtenteImpresaEntity p where p.compositePrimaryKey.idImpresa =:idImpresa ")
	public List<PLFRUtenteImpresaEntity> findByImpresa(@Param("idImpresa") BigDecimal idImpresa);
	
	@Query(value = "SELECT p from PLFRUtenteImpresaEntity p where p.compositePrimaryKey.idUtente =:idUtente ")
	public List<PLFRUtenteImpresaEntity> findByUtente(@Param("idUtente") BigDecimal idUtente);
	
	@Query(value = "SELECT p from PLFRUtenteImpresaEntity p where p.compositePrimaryKey.idUtente =:idUtente and p.dataCancellazione is null")
	public List<PLFRUtenteImpresaEntity> findByUtenteAttivo(@Param("idUtente") BigDecimal idUtente);
}
