package it.interlogic.vimp.data.jpa.repository;

import java.math.BigDecimal;

import org.springframework.data.repository.PagingAndSortingRepository;

import it.interlogic.vimp.data.jpa.model.PLFNewsImpresaTranslationEntity;

public interface PLFNewsImpresaTranslationJpaRepository extends  PagingAndSortingRepository<PLFNewsImpresaTranslationEntity, BigDecimal>
{

}
