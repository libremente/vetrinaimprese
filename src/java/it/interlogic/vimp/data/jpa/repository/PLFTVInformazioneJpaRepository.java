package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFVInformazioneEntity;
import it.interlogic.vimp.data.jpa.model.PLFVInformazioneEntityKey;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface PLFTVInformazioneJpaRepository extends PagingAndSortingRepository<PLFVInformazioneEntity, PLFVInformazioneEntityKey>,
		JpaSpecificationExecutor<PLFVInformazioneEntity>
{

	@Query(value = "SELECT i FROM PLFVInformazioneEntity i WHERE UPPER(i.ricerca) like :ricerca AND i.dataCancellazione is null ")
	public List<PLFVInformazioneEntity> findInformazioni(@Param("ricerca") String ricerca);
	
	@Query("SELECT i FROM PLFVInformazioneEntity i WHERE i.compositePrimaryKey.idTipoInformazione = :tipoInformazione AND UPPER(i.ricerca) like :ricerca AND i.dataCancellazione is null")
	public List<PLFVInformazioneEntity> findInformazioni(@Param("tipoInformazione") BigDecimal tipoInformazione, @Param("ricerca") String ricerca);

	@Query("SELECT i FROM PLFVInformazioneEntity i WHERE i.compositePrimaryKey.idTipoInformazione = :tipoInformazione AND i.compositePrimaryKey.idInformazione = :idInformazione AND i.dataCancellazione is null")
	public List<PLFVInformazioneEntity> findInformazioni(@Param("tipoInformazione") BigDecimal tipoInformazione, @Param("idInformazione") BigDecimal idInformazione);
	
	@Query("SELECT i FROM PLFVInformazioneEntity i WHERE i.compositePrimaryKey.idTipoInformazione = :tipoInformazione AND i.compositePrimaryKey.idInformazione = :idInformazione AND UPPER(i.ricerca) like :ricerca AND i.dataCancellazione is null")
	public List<PLFVInformazioneEntity> findInformazioni(@Param("tipoInformazione") BigDecimal tipoInformazione, @Param("idInformazione") BigDecimal idInformazione, @Param("ricerca") String ricerca);

}
