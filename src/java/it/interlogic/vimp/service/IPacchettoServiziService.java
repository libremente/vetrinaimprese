package it.interlogic.vimp.service;

import it.interlogic.vimp.data.jpa.model.PLFPacchettoServiziEntity;
import it.interlogic.vimp.service.exception.InformazioneDuplicataException;

import java.math.BigDecimal;
import java.util.List;

public interface IPacchettoServiziService
{
    /**
     * @param bigDecimal
     * @return
     */
    PLFPacchettoServiziEntity find(BigDecimal bigDecimal);

    /**
     * @param dettaglio
     * @return
     * @throws InformazioneDuplicataException
     */
    PLFPacchettoServiziEntity update(PLFPacchettoServiziEntity dettaglio) throws InformazioneDuplicataException;

    /**
     * @param dettaglio
     * @return
     */
    PLFPacchettoServiziEntity delete(PLFPacchettoServiziEntity dettaglio);

    /**
     * @param idInformazione
     * @return
     */
    byte[] getImage(BigDecimal idInformazione);

    /**
     * @param idInformazione
     * @param image
     */
    void updateImage(BigDecimal idInformazione, String image);

    /**
     * @param idInformazione
     */
    void deleteImage(BigDecimal idInformazione);

    /**
     * @param idInformazione
     */
    void updateAttibuti(BigDecimal idInformazione);

    /**
     * @param elencoServizi
     * @return
     */
    List<PLFPacchettoServiziEntity> findByIdsServizi(List<BigDecimal> elencoServizi);

    /**
     * @param idServizi
     * @return
     */
    List<PLFPacchettoServiziEntity> findByServizio(BigDecimal idServizi);

    /**
     * @param idServizi
     * @return
     */
    List<PLFPacchettoServiziEntity> findAttiviByServizio(BigDecimal idServizi);
    
    /**
     * @param elencoServizi
     * @return
     */
    List<PLFPacchettoServiziEntity> findByIdsServiziCompleto(List<BigDecimal> elencoServizi);
}
