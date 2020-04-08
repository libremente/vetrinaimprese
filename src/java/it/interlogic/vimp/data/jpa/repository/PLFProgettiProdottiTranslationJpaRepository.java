package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFProgettiProdottiTranslationEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;

public interface PLFProgettiProdottiTranslationJpaRepository extends PagingAndSortingRepository<PLFProgettiProdottiTranslationEntity, BigDecimal>
{
}
