package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFPacchettoServiziTranslationEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.math.BigDecimal;

public interface PLFPacchettoServiziTranslationJpaRepository extends PagingAndSortingRepository<PLFPacchettoServiziTranslationEntity, BigDecimal> {
}
