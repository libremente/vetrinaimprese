package it.interlogic.vimp.data.jpa.repository.relation;

import it.interlogic.vimp.data.jpa.model.relation.PLFRInformazioneTagEntity;
import it.interlogic.vimp.data.jpa.model.relation.PLFRInformazioneTagEntityKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public interface PLFRInformazioneTagJpaRepository extends PagingAndSortingRepository<PLFRInformazioneTagEntity, PLFRInformazioneTagEntityKey> {

    @Query(value = "SELECT IT.ID_TAG FROM PLF_R_INFORMAZIONE_TAG IT WHERE IT.ID_INFORMAZIONE = :idInformazione AND IT.ID_TIPO_INFORMAZIONE = :idTipo", nativeQuery = true)
    List<BigInteger> findIdsByInformazioneAndType(@Param("idInformazione") BigDecimal id, @Param("idTipo") BigDecimal tipo);

}
