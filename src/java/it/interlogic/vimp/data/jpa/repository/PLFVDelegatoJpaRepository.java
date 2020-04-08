package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFVDelegatoEntity;
import it.interlogic.vimp.data.jpa.model.PLFVDelegatoKeyEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFVDelegatoJpaRepository extends PagingAndSortingRepository<PLFVDelegatoEntity, PLFVDelegatoKeyEntity>, JpaSpecificationExecutor<PLFVDelegatoEntity>
{
	@Query("SELECT d FROM PLFVDelegatoEntity d WHERE d.utente.idUtente =:idUtente AND d.compositePrimaryKey.idPlfImpresa = :idPlfImpresa")
	PLFVDelegatoEntity findUtenteDelegato(@Param("idUtente") BigDecimal idUtente, @Param("idPlfImpresa") BigDecimal idPlfImpresa);

	@Query("SELECT d FROM PLFVDelegatoEntity d WHERE d.compositePrimaryKey.idPlfImpresa = :idPlfImpresa")
	List<PLFVDelegatoEntity> findUtentiDelegati(@Param("idPlfImpresa") BigDecimal idPlfImpresa);

}
