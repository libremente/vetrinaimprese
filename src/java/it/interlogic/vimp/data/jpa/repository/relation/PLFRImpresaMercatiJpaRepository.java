package it.interlogic.vimp.data.jpa.repository.relation;

import org.springframework.data.repository.PagingAndSortingRepository;

import it.interlogic.vimp.data.jpa.model.relation.PLFRImpresaMercatiEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRImpresaMercatiEntityKey;

public interface PLFRImpresaMercatiJpaRepository extends PagingAndSortingRepository<PLFRImpresaMercatiEntity, PLFRImpresaMercatiEntityKey>
{
}
