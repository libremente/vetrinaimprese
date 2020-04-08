package it.interlogic.vimp.data.jpa.model.relation;

import it.interlogic.vimp.utils.EntityUtility;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;

@Embeddable
public class PLFRProgettoSettoreProgettiProdottiEntityKey implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Column(name = "ID_PLF_PROGETTI_PRODOTTI", nullable = false)
    private BigDecimal idProgettoProdotto;

    @Column(name = "ID_PLF_T_SETTORE_PROGETTI_PRODOTTI", nullable = false)
    private BigDecimal idSettoreProgettiProdotti;

    public PLFRProgettoSettoreProgettiProdottiEntityKey(){}

    public PLFRProgettoSettoreProgettiProdottiEntityKey(BigDecimal idSettoreProgettiProdotti, BigDecimal idProgettoProdotto)
    {
        this.idSettoreProgettiProdotti = idSettoreProgettiProdotti;
        this.idProgettoProdotto = idProgettoProdotto;
    }

    public BigDecimal getIdProgettoProdotto() {
        return idProgettoProdotto;
    }

    public void setIdProgettoProdotto(BigDecimal idProgettoProdotto) {
        this.idProgettoProdotto = idProgettoProdotto;
    }

    public BigDecimal getIdSettoreProgettiProdotti() {
        return idSettoreProgettiProdotti;
    }

    public void setIdSettoreProdgettiProdotti(BigDecimal idSettoreProgettiProdotti) {
        this.idSettoreProgettiProdotti = idSettoreProgettiProdotti;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PLFRProgettoSettoreProgettiProdottiEntityKey)
        {
            PLFRProgettoSettoreProgettiProdottiEntityKey o = (PLFRProgettoSettoreProgettiProdottiEntityKey) obj;
            return EntityUtility.equals(idProgettoProdotto,o.idProgettoProdotto) &&
                    EntityUtility.equals(idSettoreProgettiProdotti,o.idSettoreProgettiProdotti);
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {idProgettoProdotto,idSettoreProgettiProdotti});
    }

}
