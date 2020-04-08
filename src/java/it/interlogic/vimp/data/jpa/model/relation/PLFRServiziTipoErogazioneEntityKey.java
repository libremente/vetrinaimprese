package it.interlogic.vimp.data.jpa.model.relation;

import it.interlogic.vimp.utils.EntityUtility;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

@Embeddable
public class PLFRServiziTipoErogazioneEntityKey implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Column(name = "ID_SERVIZI")
    private BigDecimal idServizi;

    @Column(name = "ID_TIPO_EROGAZIONE")
    private BigDecimal idTipoErogazione;

    public PLFRServiziTipoErogazioneEntityKey()
    {}

    public PLFRServiziTipoErogazioneEntityKey(BigDecimal idServizi, BigDecimal idTipoErogazione)
    {
        this.idServizi = idServizi;
        this.idTipoErogazione = idTipoErogazione;
    }

    public BigDecimal getIdServizi()
    {
        return idServizi;
    }

    public void setIdServizi(BigDecimal idServizi)
    {
        this.idServizi = idServizi;
    }

    public BigDecimal getIdTipoErogazione()
    {
        return idTipoErogazione;
    }

    public void setIdTipoErogazione(BigDecimal idTipoErogazione)
    {
        this.idTipoErogazione = idTipoErogazione;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof PLFRServiziTipoErogazioneEntityKey)
        {
            PLFRServiziTipoErogazioneEntityKey r = (PLFRServiziTipoErogazioneEntityKey) o;
            return EntityUtility.equals(idServizi,r.idServizi) && EntityUtility.equals(idTipoErogazione, r.idTipoErogazione);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return EntityUtility.hashCode(idServizi, idTipoErogazione);
    }
}
