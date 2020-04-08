package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFVRichiestaAccreditamentoEntity;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PLFVRichiestaAccreditamentoJpaRepository extends PagingAndSortingRepository<PLFVRichiestaAccreditamentoEntity, BigDecimal>,
		JpaSpecificationExecutor<PLFVRichiestaAccreditamentoEntity>
{

}