package it.interlogic.vimp.data.jpa.model.relation;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PLF_R_SERVIZI_MACROAREA")
public class PLFRServiziMacroareaEntity implements Serializable
{
	 private static final long serialVersionUID = 1L;
	 
    // ----------------------------------------------------------------------
    // ENTITY DATA FIELDS
    // ----------------------------------------------------------------------
    @EmbeddedId
    private  PLFRServiziMacroareaEntityKey compositePrimaryKey;

    public PLFRServiziMacroareaEntity()
    {
        compositePrimaryKey = new PLFRServiziMacroareaEntityKey();
    }

    public PLFRServiziMacroareaEntity(BigDecimal idServizi, BigDecimal idMacroarea)
    {
        compositePrimaryKey = new PLFRServiziMacroareaEntityKey(idServizi,idMacroarea);
    }

    public PLFRServiziMacroareaEntityKey getCompositePrimaryKey()
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

    public BigDecimal getIdMacroarea()
    {
        return compositePrimaryKey.getIdMacroarea();
    }

    public void setIdMacroarea(BigDecimal idMacroarea)
    {
        this.compositePrimaryKey.setIdMacroarea(idMacroarea);
    }


}
