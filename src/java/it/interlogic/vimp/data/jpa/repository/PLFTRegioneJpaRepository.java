package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTRegioneEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PLFTRegioneJpaRepository extends PagingAndSortingRepository<PLFTRegioneEntity, BigDecimal>
{

	@Query("SELECT i FROM PLFTRegioneEntity i WHERE i.dataFineRegione is null")
	public abstract List<PLFTRegioneEntity> findRegione();

}
