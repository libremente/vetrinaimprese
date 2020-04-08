package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTTipoInformazioneEntity;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface PLFTTipoInformazioneJpaRepository extends PagingAndSortingRepository<PLFTTipoInformazioneEntity, BigDecimal>
{

	  @Query(value="SELECT * from PLF_T_TIPO_INFORMAZIONE tipo where tipo.DATA_FINE IS NULL",nativeQuery=true) 
	  public abstract List<PLFTTipoInformazioneEntity> findTipoInformazione();

}
