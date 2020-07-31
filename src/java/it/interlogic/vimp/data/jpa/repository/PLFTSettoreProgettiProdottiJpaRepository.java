package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTSettoreProgettiProdottiEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface PLFTSettoreProgettiProdottiJpaRepository extends PagingAndSortingRepository<PLFTSettoreProgettiProdottiEntity, BigDecimal>
{
    @Query(value = "SELECT spp.ID FROM PLF_T_SETTORE_PROGETTI_PRODOTTI spp " +
            "JOIN PLF_R_PROGETTO_SETTORE_PROGETTI_PRODOTTI r ON spp.ID = r.ID_PLF_T_SETTORE_PROGETTI_PRODOTTI " +
            "WHERE r.ID_PLF_PROGETTI_PRODOTTI = :idProgetto", nativeQuery = true)
    List<BigInteger> findIdsByProgetto(@Param("idProgetto") BigDecimal idProgetto);

    @Query(value = "SELECT spp.* FROM PLF_T_SETTORE_PROGETTI_PRODOTTI spp " +
            "JOIN PLF_R_PROGETTO_SETTORE_PROGETTI_PRODOTTI r ON spp.ID = r.ID_PLF_T_SETTORE_PROGETTI_PRODOTTI " +
            "WHERE r.ID_PLF_PROGETTI_PRODOTTI = :idProgetto" , nativeQuery = true)
    List<PLFTSettoreProgettiProdottiEntity> findByProgetto(@Param("idProgetto") BigDecimal idProgetto);
    
    
    @Query(value = "SELECT spp.* FROM PLF_T_SETTORE_PROGETTI_PRODOTTI spp order by spp.DESCRIZIONE" , nativeQuery = true)
    List<PLFTSettoreProgettiProdottiEntity> findAllOrderer();

}
