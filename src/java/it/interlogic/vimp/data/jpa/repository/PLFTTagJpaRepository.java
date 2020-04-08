package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTTagEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PLFTTagJpaRepository extends PagingAndSortingRepository<PLFTTagEntity, BigDecimal>, JpaSpecificationExecutor<PLFTTagJpaRepository>
{

	@Query(value = "SELECT r from PLFTTagEntity r where (r.dataFine is null or r.dataFine > " + SQLAdapterUtil.DATE_NOW + " )")
	public abstract List<PLFTTagEntity> findTags();

}
