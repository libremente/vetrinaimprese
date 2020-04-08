package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFImpresaTranslationEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;

public interface PLFImpresaTranslationJpaRepository extends PagingAndSortingRepository<PLFImpresaTranslationEntity, BigDecimal>
{
}
