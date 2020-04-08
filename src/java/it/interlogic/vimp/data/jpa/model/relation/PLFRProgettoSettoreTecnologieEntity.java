package it.interlogic.vimp.data.jpa.model.relation;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "PLF_R_PROGETTO_SETTORE_TECNOLOGIE")
public class PLFRProgettoSettoreTecnologieEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PLFRProgettoSettoreTecnologieEntityKey compositePrimaryKey;

    public PLFRProgettoSettoreTecnologieEntity()
    {
        compositePrimaryKey = new PLFRProgettoSettoreTecnologieEntityKey();
    }

    public PLFRProgettoSettoreTecnologieEntity(BigDecimal idSettoreTecnologie, BigDecimal idPlfProgettiProdotti) {
        this.compositePrimaryKey = new PLFRProgettoSettoreTecnologieEntityKey(idSettoreTecnologie,idPlfProgettiProdotti);
    }

    public PLFRProgettoSettoreTecnologieEntityKey getCompositePrimaryKey() {
        return compositePrimaryKey;
    }

    public BigDecimal getIdProgettoProdotto()
    {
        return compositePrimaryKey.getIdProgettoProdotto();
    }

    public void setIdProgettoProdotto(BigDecimal id){
        compositePrimaryKey.setIdProgettoProdotto(id);
    }

    public BigDecimal getIdSettoreTecnologie()
    {
        return compositePrimaryKey.getIdSettoreTecnologie();
    }

    public void setIdSettoreTecnologie(BigDecimal id){
        compositePrimaryKey.setIdSettoreTecnologie(id);
    }

}
