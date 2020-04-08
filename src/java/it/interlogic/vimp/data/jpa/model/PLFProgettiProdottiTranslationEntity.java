package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the PLF_PROGETTI_PRODOTTI translated fields.
 *
 */
@Entity
@Table(name = "PLF_PROGETTI_PRODOTTI_TRANSLATION")
public class PLFProgettiProdottiTranslationEntity implements Serializable, TagEntityInterface
{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID_PLF_PROGETTI_PRODOTTI")
    private BigDecimal idPlfProgettiProdotti;

    @Column(name = "NOME_PROGETTO_PRODOTTO")
    private String nomeProgettoProdotto;

    @Column(name = "DESCRIZIONE")
    private String descrizione;

    @Column(name = "DESC_PROGETTO")
    private String descProgetto;

    @Column(name = "DESC_REQUISITI")
    private String descRequisiti;

    @Column(name = "DESC_FINALITA")
    private String descFinalita;

    @Column(name = "DESC_RISULTATI")
    private String descRisultati;

    @Column(name = "DESC_TAG")
    private String descTag;

    @Column(name = "DESC_FONTE")
    private String descFonte;

    @Column(name = "PARTNER_ALTRO")
    private String partnerAltro;
    
    @Column(name = "DESC_CONTATTI")
	private String descContatti;
    
    @Column(name = "OBIETTIVI")
	private String obiettivi;

    @Column(name = "CARATTERISTICHE_TECNICHE")
    private String caratteristicheTecniche;

    public BigDecimal getIdPlfProgettiProdotti()
    {
        return idPlfProgettiProdotti;
    }

    public void setIdPlfProgettiProdotti(BigDecimal idPlfProgettiProdotti)
    {
        this.idPlfProgettiProdotti = idPlfProgettiProdotti;
    }

    public String getNomeProgettoProdotto()
    {
        return nomeProgettoProdotto;
    }

    public void setNomeProgettoProdotto(String nomeProgettoProdotto)
    {
        this.nomeProgettoProdotto = nomeProgettoProdotto;
    }

    public String getDescrizione()
    {
        return descrizione;
    }

    public void setDescrizione(String descrizione)
    {
        this.descrizione = descrizione;
    }

    public String getDescProgetto()
    {
        return descProgetto;
    }

    public void setDescProgetto(String descProgetto)
    {
        this.descProgetto = descProgetto;
    }

    public String getDescRequisiti()
    {
        return descRequisiti;
    }

    public void setDescRequisiti(String descRequisiti)
    {
        this.descRequisiti = descRequisiti;
    }

    public String getDescFinalita()
    {
        return descFinalita;
    }

    public void setDescFinalita(String descFinalita)
    {
        this.descFinalita = descFinalita;
    }

    public String getDescRisultati()
    {
        return descRisultati;
    }

    public void setDescRisultati(String descRisultati)
    {
        this.descRisultati = descRisultati;
    }

    @Override
    public String getDescTag()
    {
        return descTag;
    }

    public void setDescTag(String descTag)
    {
        this.descTag = descTag;
    }

    public String getDescFonte()
    {
        return descFonte;
    }

    public void setDescFonte(String descFonte)
    {
        this.descFonte = descFonte;
    }

    public String getPartnerAltro()
    {
        return partnerAltro;
    }

    public void setPartnerAltro(String partnerAltro)
    {
        this.partnerAltro = partnerAltro;
    }

	public String getDescContatti()
	{
		return descContatti;
	}

	public void setDescContatti(String descContatti)
	{
		this.descContatti = descContatti;
	}
	
    public String getObiettivi()
    {
        return obiettivi;
    }

    public void setObiettivi(String obiettivi)
    {
        this.obiettivi = obiettivi;
    }

    public String getCaratteristicheTecniche() {
        return caratteristicheTecniche;
    }

    public void setCaratteristicheTecniche(String caratteristicheTecniche) {
        this.caratteristicheTecniche = caratteristicheTecniche;
    }
}
