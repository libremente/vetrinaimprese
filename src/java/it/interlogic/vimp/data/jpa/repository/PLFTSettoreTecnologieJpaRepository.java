package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTSettoreTecnologieEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface PLFTSettoreTecnologieJpaRepository extends PagingAndSortingRepository<PLFTSettoreTecnologieEntity, BigDecimal>
{
    @Query(value = "SELECT spt.ID FROM PLF_T_SETTORE_TECNOLOGIE spt " +
            "JOIN PLF_R_PROGETTO_SETTORE_TECNOLOGIE r ON spt.ID = r.ID_PLF_T_SETTORE_TECNOLOGIE " +
            "WHERE r.ID_PLF_PROGETTI_PRODOTTI = :idProgetto", nativeQuery = true)
    List<BigInteger> findIdsByProgetto(@Param("idProgetto") BigDecimal idProgetto);

    @Query(value = "SELECT spt.* FROM PLF_T_SETTORE_TECNOLOGIE spt " +
            "JOIN PLF_R_PROGETTO_SETTORE_TECNOLOGIE r ON spt.ID = r.ID_PLF_T_SETTORE_TECNOLOGIE " +
            "WHERE r.ID_PLF_PROGETTI_PRODOTTI = :idProgetto", nativeQuery = true)
    List<PLFTSettoreTecnologieEntity> findByProgetto(@Param("idProgetto") BigDecimal idProgetto);

}
