package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the PLF_COLLABORAZIONI database table.
 * 
 */
@Entity
@Table(name = "PLF_COLLABORAZIONI")
@NamedQuery(name = "PLFCollaborazioniEntity.findAll", query = "SELECT p FROM PLFCollaborazioniEntity p")
public class PLFCollaborazioniEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PLF_COLLABORAZIONI")
	private BigDecimal idPlfCollaborazioni;

	@Column(name = "CODICE_FISCALE")
	private String codiceFiscale;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_FINE_COLLABORAZIONE")
	private Date dataFineCollaborazione;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_INIZIO_COLLABORAZIONE")
	private Date dataInizioCollaborazione;

	@Column(name = "PARTITA_IVA")
	private String partitaIva;

	@Column(name = "RAGIONE_SOCIALE")
	private String ragioneSociale;

	// bi-directional many-to-one association to PLFImpresa
	@ManyToOne
	@JoinColumn(name = "ID_PLF_IMPRESA")
	private PLFImpresaEntity plfImpresa;

	// bi-directional many-to-one association to PLFTNazione
	@ManyToOne
	@JoinColumn(name = "ID_PLF_T_NAZIONE")
	private PLFTNazioneEntity plfTNazione;

	// bi-directional many-to-one association to PLFProgettiProdotti
	@ManyToOne
	@JoinColumn(name = "ID_PLF_PROGETTI_PRODOTTI")
	private PLFProgettiProdottiEntity plfProgettiProdotti;

	// bi-directional many-to-one association to PLFTComune
	@ManyToOne
	@JoinColumn(name = "ID_PLF_T_COMUNE")
	private PLFTComuneEntity plfTComune;

	public PLFCollaborazioniEntity()
	{
	}

	public BigDecimal getIdPlfCollaborazioni()
	{
		return this.idPlfCollaborazioni;
	}

	public void setIdPlfCollaborazioni(BigDecimal idPlfCollaborazioni)
	{
		this.idPlfCollaborazioni = idPlfCollaborazioni;
	}

	public String getCodiceFiscale()
	{
		return this.codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale)
	{
		this.codiceFiscale = codiceFiscale;
	}

	public Date getDataFineCollaborazione()
	{
		return this.dataFineCollaborazione;
	}

	public void setDataFineCollaborazione(Date dataFineCollaborazione)
	{
		this.dataFineCollaborazione = dataFineCollaborazione;
	}

	public Date getDataInizioCollaborazione()
	{
		return this.dataInizioCollaborazione;
	}

	public void setDataInizioCollaborazione(Date dataInizioCollaborazione)
	{
		this.dataInizioCollaborazione = dataInizioCollaborazione;
	}

	public String getPartitaIva()
	{
		return this.partitaIva;
	}

	public void setPartitaIva(String partitaIva)
	{
		this.partitaIva = partitaIva;
	}

	public String getRagioneSociale()
	{
		return this.ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale)
	{
		this.ragioneSociale = ragioneSociale;
	}

	public PLFImpresaEntity getPlfImpresa()
	{
		return this.plfImpresa;
	}

	public void setPlfImpresa(PLFImpresaEntity plfImpresa)
	{
		this.plfImpresa = plfImpresa;
	}

	public PLFTNazioneEntity getPlfTNazione()
	{
		return this.plfTNazione;
	}

	public void setPlfTNazione(PLFTNazioneEntity plfTNazione)
	{
		this.plfTNazione = plfTNazione;
	}

	public PLFProgettiProdottiEntity getPlfProgettiProdotti()
	{
		return this.plfProgettiProdotti;
	}

	public void setPlfProgettiProdotti(PLFProgettiProdottiEntity plfProgettiProdotti)
	{
		this.plfProgettiProdotti = plfProgettiProdotti;
	}

	public PLFTComuneEntity getPlfTComune()
	{
		return this.plfTComune;
	}

	public void setPlfTComune(PLFTComuneEntity plfTComune)
	{
		this.plfTComune = plfTComune;
	}

	@Override
	public String toString()
	{
		return "PLFCollaborazioniEntity [idPlfCollaborazioni=" + idPlfCollaborazioni + "]";
	}

}