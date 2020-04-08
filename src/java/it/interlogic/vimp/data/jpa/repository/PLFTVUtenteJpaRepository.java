package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFVUtenteEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface PLFTVUtenteJpaRepository extends PagingAndSortingRepository<PLFVUtenteEntity, BigDecimal>, JpaSpecificationExecutor<PLFVUtenteEntity> {

	@Query(value = "SELECT i FROM PLFVUtenteEntity i WHERE UPPER(i.ricerca) like :ricerca ")
	public List<PLFVUtenteEntity> findUtenti(@Param("ricerca") String ricerca);

}
