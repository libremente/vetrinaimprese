package it.interlogic.vimp.data.jpa.model.relation;

import it.interlogic.vimp.utils.EntityUtility;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PLFRInformazioneTagEntityKey implements Serializable {

    // ----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    // ----------------------------------------------------------------------
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_INFORMAZIONE", nullable = false)
    private BigDecimal idInformazione;

    @Column(name = "ID_TIPO_INFORMAZIONE", nullable = false)
    private BigDecimal idTipoInformazione;

    @Column(name = "ID_TAG", nullable = false)
    private BigDecimal idTag;

    public PLFRInformazioneTagEntityKey() {}

    public PLFRInformazioneTagEntityKey(BigDecimal idInformazione, BigDecimal idTipoInformazione, BigDecimal idTag) {
        this.idInformazione = idInformazione;
        this.idTipoInformazione = idTipoInformazione;
        this.idTag = idTag;
    }

    public BigDecimal getIdInformazione() {
        return idInformazione;
    }

    public void setIdInformazione(BigDecimal idInformazione) {
        this.idInformazione = idInformazione;
    }

    public BigDecimal getIdTipoInformazione() {
        return idTipoInformazione;
    }

    public void setIdTipoInformazione(BigDecimal idTipoInformazione) {
        this.idTipoInformazione = idTipoInformazione;
    }

    public BigDecimal getIdTag() {
        return idTag;
    }

    public void setIdTag(BigDecimal idTag) {
        this.idTag = idTag;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PLFRInformazioneTagEntityKey)
        {
            PLFRInformazioneTagEntityKey o = (PLFRInformazioneTagEntityKey) obj;
            return EntityUtility.equals(idInformazione,o.idInformazione) &&
                    EntityUtility.equals(idTipoInformazione,o.idTipoInformazione) &&
                    EntityUtility.equals(idTag, o.idTag);
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {idInformazione, idTipoInformazione, idTag});
    }

    @Override
    public String toString()
    {
        return "PLFRInformazioneTagEntityKey [idInformazione=" + idInformazione + ", idTipoInformazione=" + idTipoInformazione +
                ", idTag=" + idTag + "]";
    }

}
