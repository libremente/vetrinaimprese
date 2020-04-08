package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the PLF_NEWS_IMPRESA translated columns.
 *
 */
@Entity
@Table(name = "PLF_NEWS_IMPRESA_TRANSLATION")
public class PLFNewsImpresaTranslationEntity implements Serializable, TagEntityInterface {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_NEWS_IMPRESA")
    private BigDecimal idNewsImpresa;

    @Column(name = "DATA_TESTO")
    private String dataTesto;

    @Column(name = "DESCRIZIONE")
    private String descrizione;

    public BigDecimal getIdNewsImpresa() {
        return idNewsImpresa;
    }

    public void setIdNewsImpresa(BigDecimal idNewsImpresa) {
        this.idNewsImpresa = idNewsImpresa;
    }

    public String getDataTesto() {
        return dataTesto;
    }

    public void setDataTesto(String dataTesto) {
        this.dataTesto = dataTesto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String getDescTag() {
        return descrizione;
    }

}
