package it.interlogic.vimp.data.jpa.repository.relation;

import it.interlogic.vimp.data.jpa.model.relation.PLFRPacchettoServiziEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRPacchettoServiziEntityKey;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFRPacchettoServiziJpaRepository extends PagingAndSortingRepository<PLFRPacchettoServiziEntity, PLFRPacchettoServiziEntityKey>
{
	@Query(value = "DELETE FROM PLF_R_PACCHETTO_SERVIZI WHERE ID_SERVIZI = :idServizi", nativeQuery = true)
	void deleteRPacchettoServiziByServizio(@Param("idServizi") BigDecimal idServizi);

	@Query("SELECT i FROM PLFRPacchettoServiziEntity i WHERE i.compositePrimaryKey.idServizi = :idServizi")
	List<PLFRPacchettoServiziEntity> findRPacchettoServiziByServizio(@Param("idServizi") BigDecimal idServizi);
	
	
	@Query("SELECT i FROM PLFRPacchettoServiziEntity i WHERE i.compositePrimaryKey.idPacchettoServizi = :idPacchettoServizi")
	List<PLFRPacchettoServiziEntity> findRPacchettoServiziByPacchetto(@Param("idPacchettoServizi") BigDecimal idPacchettoServizi);
}
