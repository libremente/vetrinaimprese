package it.interlogic.vimp.data.jpa.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.interlogic.vimp.data.jpa.model.PLFTDenominazioneServiziEntity;

public interface PLFTDenominazioneServiziJpaRepository extends PagingAndSortingRepository<PLFTDenominazioneServiziEntity, BigDecimal> {

	@Query(value= "SELECT d FROM PLFTDenominazioneServiziEntity d WHERE dataFine IS NULL")
	List<PLFTDenominazioneServiziEntity> findModalitaErogazione();

}
