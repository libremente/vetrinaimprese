package it.interlogic.vimp.data.jpa.model.relation;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;

@Embeddable
public class PLFRProgettoSettoreTecnologieEntityKey implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Column(name = "ID_PLF_PROGETTI_PRODOTTI", nullable = false)
    private BigDecimal idProgettoProdotto;

    @Column(name = "ID_PLF_T_SETTORE_TECNOLOGIE", nullable = false)
    private BigDecimal idSettoreTecnologie;

    public PLFRProgettoSettoreTecnologieEntityKey(){}

    public PLFRProgettoSettoreTecnologieEntityKey(BigDecimal idSettoreTecnologie, BigDecimal idProgettiProdotti) {
        this.idSettoreTecnologie = idSettoreTecnologie;
        this.idProgettoProdotto = idProgettiProdotti;
    }

    public BigDecimal getIdProgettoProdotto() {
        return idProgettoProdotto;
    }

    public void setIdProgettoProdotto(BigDecimal idProgettoProdotto) {
        this.idProgettoProdotto = idProgettoProdotto;
    }

    public BigDecimal getIdSettoreTecnologie() {
        return idSettoreTecnologie;
    }

    public void setIdSettoreTecnologie(BigDecimal idSettoreTecnologie) {
        this.idSettoreTecnologie = idSettoreTecnologie;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PLFRProgettoSettoreTecnologieEntityKey)
        {
            PLFRProgettoSettoreTecnologieEntityKey o = (PLFRProgettoSettoreTecnologieEntityKey) obj;
            return getIdProgettoProdotto()!=null
                    && getIdSettoreTecnologie()!=null
                    && o.getIdProgettoProdotto()!=null
                    && o.getIdSettoreTecnologie()!=null
                    && getIdProgettoProdotto().equals(o.getIdProgettoProdotto())
                    && getIdSettoreTecnologie().equals(o.getIdSettoreTecnologie());
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(new Object[] {idProgettoProdotto,idSettoreTecnologie});
    }

}
