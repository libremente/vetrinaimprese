package it.interlogic.vimp.data.jpa.model.relation;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PLF_R_SERVIZI_TIPO_EROGAZIONE")
public class PLFRServiziTipoErogazioneEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

    // ----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    // ----------------------------------------------------------------------
    @EmbeddedId
    private  PLFRServiziTipoErogazioneEntityKey compositePrimaryKey;

    public PLFRServiziTipoErogazioneEntity(){}

    public PLFRServiziTipoErogazioneEntity(BigDecimal idServizi, BigDecimal idTipoErogazione)
    {
        compositePrimaryKey = new PLFRServiziTipoErogazioneEntityKey(idServizi,idTipoErogazione);
    }

    public PLFRServiziTipoErogazioneEntityKey getCompositePrimaryKey()
    {
        return this.compositePrimaryKey;
    }

    public BigDecimal getIdServizi()
    {
        return compositePrimaryKey.getIdServizi();
    }

    public void setIdServizi(BigDecimal idServizi)
    {
        this.compositePrimaryKey.setIdServizi(idServizi);
    }

    public BigDecimal getIdTipoErogazione()
    {
        return compositePrimaryKey.getIdTipoErogazione();
    }

    public void setIdTipoErogazione(BigDecimal idTipoErogazione)
    {
        this.compositePrimaryKey.setIdTipoErogazione(idTipoErogazione);
    }

}
