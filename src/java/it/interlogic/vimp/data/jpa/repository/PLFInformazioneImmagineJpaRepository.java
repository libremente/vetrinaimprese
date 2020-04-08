package it.interlogic.vimp.data.jpa.repository;

import it.interlogic.vimp.data.jpa.model.PLFInformazioneImmagineEntity;
import it.interlogic.vimp.data.jpa.model.PLFInformazioneImmagineEntityKey;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface PLFInformazioneImmagineJpaRepository extends PagingAndSortingRepository<PLFInformazioneImmagineEntity, PLFInformazioneImmagineEntityKey>
{
}
