package it.interlogic.vimp.data.jpa.repository.relation;

import org.springframework.data.repository.PagingAndSortingRepository;

import it.interlogic.vimp.data.jpa.model.relation.PLFRImpresaInnovazioneEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRImpresaInnovazioneEntityKey;

public interface PLFRImpresaInnovazioneJpaRepository extends PagingAndSortingRepository<PLFRImpresaInnovazioneEntity, PLFRImpresaInnovazioneEntityKey>
{
}
