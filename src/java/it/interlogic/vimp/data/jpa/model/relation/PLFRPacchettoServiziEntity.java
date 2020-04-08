package it.interlogic.vimp.data.jpa.model.relation;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "PLF_R_PACCHETTO_SERVIZI")
public class PLFRPacchettoServiziEntity implements Serializable
{
	 private static final long serialVersionUID = 1L;

    // ----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    // ----------------------------------------------------------------------
    @EmbeddedId
    private PLFRPacchettoServiziEntityKey compositePrimaryKey;

    public PLFRPacchettoServiziEntity()
    {
        compositePrimaryKey = new PLFRPacchettoServiziEntityKey();
    }

    public PLFRPacchettoServiziEntity(BigDecimal idServizi, BigDecimal idPacchettoServizi)
    {
        compositePrimaryKey = new PLFRPacchettoServiziEntityKey(idServizi,idPacchettoServizi);
    }

    public PLFRPacchettoServiziEntityKey getCompositePrimaryKey()
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

    public BigDecimal getIdPacchettoServizi()
    {
        return compositePrimaryKey.getIdPacchettoServizi();
    }

    public void setIdPacchettoServizi(BigDecimal idPacchettoServizi)
    {
        this.compositePrimaryKey.setIdPacchettoServizi(idPacchettoServizi);
    }


}
