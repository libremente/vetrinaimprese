package it.interlogic.vimp.data.jpa.repository;

import java.math.BigDecimal;

import org.springframework.data.repository.PagingAndSortingRepository;

import it.interlogic.vimp.data.jpa.model.PLFServiziTranslationEntity;

public interface PLFServiziTranslationJpaRepository extends PagingAndSortingRepository<PLFServiziTranslationEntity, BigDecimal>
{
}
