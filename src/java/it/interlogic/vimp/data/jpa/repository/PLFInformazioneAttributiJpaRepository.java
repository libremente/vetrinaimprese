package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFInformazioneAttributiEntity;
import it.interlogic.vimp.data.jpa.model.PLFInformazioneAttributiEntityKey;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PLFInformazioneAttributiJpaRepository extends PagingAndSortingRepository<PLFInformazioneAttributiEntity, PLFInformazioneAttributiEntityKey>
{
	@Query(value = "SELECT max(NUMERO_VISITE) FROM PLF_INFORMAZIONE_ATTRIBUTI ", nativeQuery = true)
	BigInteger numeroVisiteNextval();
}
