package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFParameter;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFParameterJpaRepository extends PagingAndSortingRepository<PLFParameter, BigDecimal>, JpaSpecificationExecutor<PLFParameter>
{

	@Query("SELECT i FROM PLFParameter i WHERE i.name = :name")
	List<PLFParameter> findParamByName(@Param("name") String name);
}