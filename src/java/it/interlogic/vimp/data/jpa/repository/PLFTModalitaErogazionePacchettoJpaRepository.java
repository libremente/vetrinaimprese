package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTModalitaErogazionePacchettoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;
import java.util.List;

public interface PLFTModalitaErogazionePacchettoJpaRepository extends PagingAndSortingRepository<PLFTModalitaErogazionePacchettoEntity, BigDecimal>
{
    @Query(value = "SELECT m FROM PLFTModalitaErogazionePacchettoEntity m WHERE dataFine IS NULL")
    List<PLFTModalitaErogazionePacchettoEntity> findModalitaErogazionePacchetto();
}
