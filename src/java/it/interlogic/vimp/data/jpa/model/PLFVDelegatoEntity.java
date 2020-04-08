package it.interlogic.vimp.data.jpa.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PLF_V_DELEGATO")
public class PLFVDelegatoEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    public static final int TIPO_DELEGATO_IMPRESA = 0;
    public static final int TIPO_DELEGATO_STAKEHOLDER = 1;

    @EmbeddedId
    private PLFVDelegatoKeyEntity compositePrimaryKey;

    @Column(name = "LEGALE_RAPPRESENTANTE")
    private boolean legaleRappresentante;

    @Column(name = "TIPO_DELEGATO")
    private Integer tipoDelegato;

    @ManyToOne
    @JoinColumn(name = "ID_UTENTE", referencedColumnName = "ID_UTENTE")
    private PLFTUtenteEntity utente;

    public boolean isImpresa(){
        return tipoDelegato == null || tipoDelegato == TIPO_DELEGATO_IMPRESA;
    }

    public boolean isStakeholder(){
        return tipoDelegato !=null && tipoDelegato == TIPO_DELEGATO_STAKEHOLDER;
    }

    public PLFVDelegatoKeyEntity getCompositePrimaryKey() {
        return compositePrimaryKey;
    }

    public void setCompositePrimaryKey(PLFVDelegatoKeyEntity compositePrimaryKey) {
        this.compositePrimaryKey = compositePrimaryKey;
    }

    public boolean isLegaleRappresentante() {
        return legaleRappresentante;
    }

    public void setLegaleRappresentante(boolean legaleRappresentante) {
        this.legaleRappresentante = legaleRappresentante;
    }

    public Integer getTipoDelegato() {
        return tipoDelegato;
    }

    public void setTipoDelegato(Integer tipoDelegato) {
        this.tipoDelegato = tipoDelegato;
    }

    public PLFTUtenteEntity getUtente() {
        return utente;
    }

    public void setUtente(PLFTUtenteEntity utente) {
        this.utente = utente;
    }

}
