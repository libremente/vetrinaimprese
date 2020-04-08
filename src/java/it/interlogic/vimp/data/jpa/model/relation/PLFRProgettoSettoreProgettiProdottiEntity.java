package it.interlogic.vimp.data.jpa.model.relation;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "PLF_R_PROGETTO_SETTORE_PROGETTI_PRODOTTI")
public class PLFRProgettoSettoreProgettiProdottiEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PLFRProgettoSettoreProgettiProdottiEntityKey compositePrimaryKey;

    public PLFRProgettoSettoreProgettiProdottiEntity()
    {
        compositePrimaryKey = new PLFRProgettoSettoreProgettiProdottiEntityKey();
    }

    public PLFRProgettoSettoreProgettiProdottiEntity(BigDecimal idSettoreProgettiProdotti, BigDecimal idPlfProgettiProdotti) {
        compositePrimaryKey = new PLFRProgettoSettoreProgettiProdottiEntityKey(idSettoreProgettiProdotti,idPlfProgettiProdotti);
    }

    public PLFRProgettoSettoreProgettiProdottiEntityKey getCompositePrimaryKey() {
        return compositePrimaryKey;
    }

    public BigDecimal getIdProgettoProdotto()
    {
        return compositePrimaryKey.getIdProgettoProdotto();
    }

    public void setIdProgettoProdotto(BigDecimal id){
        compositePrimaryKey.setIdProgettoProdotto(id);
    }

    public BigDecimal getIdSettoreProgettiProdotti()
    {
        return compositePrimaryKey.getIdSettoreProgettiProdotti();
    }

    public void setIdSettoreProgettiProdotti(BigDecimal id){
        compositePrimaryKey.setIdSettoreProdgettiProdotti(id);
    }

}
