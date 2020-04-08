package it.interlogic.vimp.data.jpa.model.relation;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import it.interlogic.vimp.utils.EntityUtility;

@Embeddable
public class PLFRServiziMacroareaEntityKey implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Column(name = "ID_SERVIZI")
    private BigDecimal idServizi;

    @Column(name = "ID_MACROAREA")
    private BigDecimal idMacroarea;

    public PLFRServiziMacroareaEntityKey()
    {}

    public PLFRServiziMacroareaEntityKey(BigDecimal idServizi, BigDecimal idMacroarea)
    {
        this.idServizi = idServizi;
        this.idMacroarea = idMacroarea;
    }

    public BigDecimal getIdServizi()
    {
        return idServizi;
    }

    public void setIdServizi(BigDecimal idServizi)
    {
        this.idServizi = idServizi;
    }

    public BigDecimal getIdMacroarea()
    {
        return idMacroarea;
    }

    public void setIdMacroarea(BigDecimal idMacroarea)
    {
        this.idMacroarea = idMacroarea;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof PLFRServiziMacroareaEntityKey)
        {
            PLFRServiziMacroareaEntityKey r = (PLFRServiziMacroareaEntityKey) o;
            return EntityUtility.equals(idServizi,r.idServizi) && EntityUtility.equals(idMacroarea, r.idMacroarea);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return EntityUtility.hashCode(idServizi, idMacroarea);
    }
}
