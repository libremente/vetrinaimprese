package it.interlogic.vimp.data.jpa.model.relation;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "PLF_R_INFORMAZIONE_TAG")
public class PLFRInformazioneTagEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PLFRInformazioneTagEntityKey compositePrimaryKey;

    public PLFRInformazioneTagEntity()
    {
        compositePrimaryKey = new PLFRInformazioneTagEntityKey();
    }

    public PLFRInformazioneTagEntity(BigDecimal idInformazione, BigDecimal idTipoInformazione, BigDecimal idTag) {
        compositePrimaryKey = new PLFRInformazioneTagEntityKey(idInformazione, idTipoInformazione, idTag);
    }

    public PLFRInformazioneTagEntityKey getCompositePrimaryKey() {
        return compositePrimaryKey;
    }

    public BigDecimal getIdInformazione()
    {
        return compositePrimaryKey.getIdInformazione();
    }

    public void setIdInformazione(BigDecimal id){
        compositePrimaryKey.setIdInformazione(id);
    }

    public BigDecimal getIdTipoInformazione()
    {
        return compositePrimaryKey.getIdTipoInformazione();
    }

    public void setIdTipoInformazione(BigDecimal id){
        compositePrimaryKey.setIdTipoInformazione(id);
    }

    public BigDecimal getIdTag() { return compositePrimaryKey.getIdTag(); }

    public void setIdTag(BigDecimal id) { compositePrimaryKey.setIdTag(id); }

}
