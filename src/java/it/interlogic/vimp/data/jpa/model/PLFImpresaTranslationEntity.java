package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity comprising internationalized fields from {@link PLFImpresaEntity}
 * */
@Entity
@Table(name = "PLF_IMPRESA_TRANSLATION")
public class PLFImpresaTranslationEntity implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_PLF_IMPRESA")
    private BigDecimal idPlfImpresa;

    @Column(name = "BREVETTO")
    private String brevetto;

    @Column(name = "DESC_ATTIVITA")
    private String descAttivita;

    @Column(name = "DESC_BREVE_IMPRESA")
    private String descBreveImpresa;

    @Column(name = "DESC_FONTE")
    private String descFonte;

    @Column(name = "DESC_IMPRESA")
    private String descImpresa;

    @Column(name = "DESC_MISSIONE")
    private String descMissione;

    @Column(name = "DESC_TAG")
    private String descTag;

    @Column(name = "ELEMENTI_INNOVAZIONE_ALTRO")
    private String elementiInnovazioneAltro;

    @Column(name = "MERCATI_ALTRO")
    private String mercatiAltro;

    @Column(name = "RUOLO_CONTATTO_ALTRO")
    private String ruoloContattoAltro;

    public String getBrevetto() {
        return brevetto;
    }

    public void setBrevetto(String brevetto) {
        this.brevetto = brevetto;
    }

    public BigDecimal getIdPlfImpresa() {
        return idPlfImpresa;
    }

    public void setIdPlfImpresa(BigDecimal idPlfImpresa) {
        this.idPlfImpresa = idPlfImpresa;
    }

    public String getDescAttivita() {
        return descAttivita;
    }

    public void setDescAttivita(String descAttivita) {
        this.descAttivita = descAttivita;
    }

    public String getDescBreveImpresa() {
        return descBreveImpresa;
    }

    public void setDescBreveImpresa(String descBreveImpresa) {
        this.descBreveImpresa = descBreveImpresa;
    }

    public String getDescFonte() {
        return descFonte;
    }

    public void setDescFonte(String descFonte) {
        this.descFonte = descFonte;
    }

    public String getDescImpresa() {
        return descImpresa;
    }

    public void setDescImpresa(String descImpresa) {
        this.descImpresa = descImpresa;
    }

    public String getDescMissione() {
        return descMissione;
    }

    public void setDescMissione(String descMissione) {
        this.descMissione = descMissione;
    }

    public String getDescTag() {
        return descTag;
    }

    public void setDescTag(String descTag) {
        this.descTag = descTag;
    }

    public String getElementiInnovazioneAltro() {
        return elementiInnovazioneAltro;
    }

    public void setElementiInnovazioneAltro(String elementiInnovazioneAltro) {
        this.elementiInnovazioneAltro = elementiInnovazioneAltro;
    }

    public String getMercatiAltro() {
        return mercatiAltro;
    }

    public void setMercatiAltro(String mercatiAltro) {
        this.mercatiAltro = mercatiAltro;
    }

    public String getRuoloContattoAltro() {
        return ruoloContattoAltro;
    }

    public void setRuoloContattoAltro(String ruoloContattoAltro) {
        this.ruoloContattoAltro = ruoloContattoAltro;
    }
}
