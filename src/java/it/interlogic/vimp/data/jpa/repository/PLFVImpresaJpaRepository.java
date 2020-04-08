package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFVImpresaEntity;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PLFVImpresaJpaRepository extends PagingAndSortingRepository<PLFVImpresaEntity, BigDecimal>, JpaSpecificationExecutor<PLFVImpresaEntity>
{

}