package it.interlogic.vimp.data.jpa.repository.relation;

import org.springframework.data.repository.PagingAndSortingRepository;

import it.interlogic.vimp.data.jpa.model.relation.PLFRUtenteImpresaEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRUtenteImpresaEntityKey;

public interface PLFRUtenteImpresaJpaRepository extends PagingAndSortingRepository<PLFRUtenteImpresaEntity, PLFRUtenteImpresaEntityKey>
{
}
