package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFPacchettoServiziEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PLFPacchettoServiziJpaRepository extends PagingAndSortingRepository<PLFPacchettoServiziEntity, BigDecimal> {

    @Query( value = "SELECT PAC.* FROM PLF_PACCHETTO_SERVIZI PAC WHERE PAC.DATA_FINE IS NULL AND PAC.ID_PACCHETTO_SERVIZI IN ( " +
            "SELECT ASS.ID_PACCHETTO_SERVIZI FROM " +
            "PLF_R_PACCHETTO_SERVIZI ASS WHERE ASS.ID_SERVIZI IN (:ids) AND PAC.DATA_CANCELLAZIONE IS NULL " +
            "GROUP BY ASS.ID_PACCHETTO_SERVIZI HAVING COUNT(DISTINCT ASS.ID_SERVIZI) = :n " +
            " AND (PAC.DATA_FINE IS NULL OR PAC.DATA_FINE >= " + SQLAdapterUtil.DATE_NOW + "))", nativeQuery = true)
    List<PLFPacchettoServiziEntity> findByIdsServizi(@Param("ids") List<BigDecimal> ids, @Param("n") int n);
    
    
    @Query( value = "SELECT PAC.* FROM PLF_PACCHETTO_SERVIZI PAC WHERE PAC.DATA_FINE IS NULL AND PAC.ID_PACCHETTO_SERVIZI IN ( " +
            "SELECT ASS.ID_PACCHETTO_SERVIZI FROM " +
            "PLF_R_PACCHETTO_SERVIZI ASS WHERE PAC.DATA_CANCELLAZIONE IS NULL AND ASS.ID_SERVIZI IN (:ids) " +
            ")", nativeQuery = true)
    List<PLFPacchettoServiziEntity> findByIdsServiziCompleto(@Param("ids") List<BigDecimal> ids);
    

    @Query( value = "SELECT PAC.* FROM PLF_PACCHETTO_SERVIZI PAC JOIN " +
            "PLF_R_PACCHETTO_SERVIZI ASS ON ASS.ID_PACCHETTO_SERVIZI = PAC.ID_PACCHETTO_SERVIZI " +
            "WHERE PAC.DATA_CANCELLAZIONE IS NULL AND ASS.ID_SERVIZI = :idServizi", nativeQuery = true)
    List<PLFPacchettoServiziEntity> findByServizio(@Param("idServizi")BigDecimal idServizi);

    @Query( value = "SELECT PAC.* FROM PLF_PACCHETTO_SERVIZI PAC JOIN " +
            "PLF_R_PACCHETTO_SERVIZI ASS ON ASS.ID_PACCHETTO_SERVIZI = PAC.ID_PACCHETTO_SERVIZI " +
            "WHERE PAC.DATA_CANCELLAZIONE IS NULL AND ASS.ID_SERVIZI = :idServizi AND " +
            "(PAC.DATA_FINE IS NULL OR PAC.DATA_FINE >= " + SQLAdapterUtil.DATE_NOW + ")", nativeQuery = true)
    List<PLFPacchettoServiziEntity> findAttiviByServizio(@Param("idServizi")BigDecimal idServizi);
    
    
    
    
}
