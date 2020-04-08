package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFTLogAuditEntity;

import java.math.BigDecimal;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PLFTLogAuditJpaRepository extends PagingAndSortingRepository<PLFTLogAuditEntity, BigDecimal>
{
}
