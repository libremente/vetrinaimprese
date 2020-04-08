package it.interlogic.vimp.data.jpa.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;

@Embeddable
public class PLFVDelegatoKeyEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "CODICE_FISCALE")
    private String codiceFiscale;

    @Column(name = "ID_PLF_IMPRESA")
    private BigDecimal idPlfImpresa;

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public BigDecimal getIdPlfImpresa() {
        return idPlfImpresa;
    }

    public void setIdPlfImpresa(BigDecimal idPlfImpresa) {
        this.idPlfImpresa = idPlfImpresa;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PLFVDelegatoKeyEntity)
        {
            PLFVDelegatoKeyEntity o = (PLFVDelegatoKeyEntity) obj;
            return getCodiceFiscale()!=null
                    && getIdPlfImpresa()!=null
                    && o.getCodiceFiscale()!=null
                    && o.getIdPlfImpresa()!=null
                    && getCodiceFiscale().equals(o.getCodiceFiscale())
                    && getIdPlfImpresa().equals(o.getIdPlfImpresa());
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {idPlfImpresa,codiceFiscale});
    }
}
