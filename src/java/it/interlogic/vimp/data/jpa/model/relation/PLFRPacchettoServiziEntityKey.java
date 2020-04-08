package it.interlogic.vimp.data.jpa.model.relation;

import it.interlogic.vimp.utils.EntityUtility;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

@Embeddable
public class PLFRPacchettoServiziEntityKey implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Column(name = "ID_SERVIZI")
    private BigDecimal idServizi;

    @Column(name = "ID_PACCHETTO_SERVIZI")
    private BigDecimal idPacchettoServizi;

    public PLFRPacchettoServiziEntityKey()
    {}

    public PLFRPacchettoServiziEntityKey(BigDecimal idServizi, BigDecimal idPacchettoServizi)
    {
        this.idServizi = idServizi;
        this.idPacchettoServizi = idPacchettoServizi;
    }

    public BigDecimal getIdServizi()
    {
        return idServizi;
    }

    public void setIdServizi(BigDecimal idServizi)
    {
        this.idServizi = idServizi;
    }

    public BigDecimal getIdPacchettoServizi()
    {
        return idPacchettoServizi;
    }

    public void setIdPacchettoServizi(BigDecimal idPacchettoServizi)
    {
        this.idPacchettoServizi = idPacchettoServizi;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof PLFRPacchettoServiziEntityKey)
        {
            PLFRPacchettoServiziEntityKey r = (PLFRPacchettoServiziEntityKey) o;
            return EntityUtility.equals(idServizi,r.idServizi) && EntityUtility.equals(idPacchettoServizi, r.idPacchettoServizi);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return EntityUtility.hashCode(idServizi, idPacchettoServizi);
    }
}
