package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.springframework.web.util.HtmlUtils;

@Entity
@Table(name = "PLF_V_INFORMAZIONE")
// Define named queries here
@NamedQueries({ @NamedQuery(name = "PLFVInformazioneEntity.countAll", query = "SELECT COUNT(x) FROM PLFVInformazioneEntity x") })
public class PLFVInformazioneEntity implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------

	@EmbeddedId
	private PLFVInformazioneEntityKey compositePrimaryKey;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------

	@Column(name = "TITOLO")
	private String titolo;

	@Column(name = "PRIMA")
	private String prima;

	@Column(name = "SECONDA")
	private String seconda;

	@Column(name = "TERZA")
	private String terza;

	@Column(name = "IMMAGINE")
	private byte[] immagine;

	@Column(name = "RICERCA")
	private String ricerca;

	@Column(name = "STATO")
	private BigDecimal stato;

	@Column(name = "DATA_AGGIORNAMENTO")
	private Date dataAggiornamento;

	@Column(name = "NUMERO_VISITE")
	private BigDecimal numeroVisite;

	@Column(name = "DATA_ULTIMA_VISITA")
	private Date dataUltimaVisita;

	@Column(name = "PUBBLICATO")
	private boolean pubblicato;

	@Column(name = "SCADENZA")
	private Date scadenza;

	@Column(name = "SORT_SCADENZA")
	private BigDecimal sortScadenza;
	
	@Column(name = "DATA_CANCELLAZIONE")
	private Date dataCancellazione;

	// ----------------------------------------------------------------------
	// ENTITY LINKS ( RELATIONSHIP )
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFVInformazioneEntity()
	{
		super();
		this.compositePrimaryKey = new PLFVInformazioneEntityKey();
	}

	public PLFVInformazioneEntityKey getCompositePrimaryKey()
	{
		return this.compositePrimaryKey;
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------

	public void setIdInformazione(BigDecimal idInformazione)
	{
		this.compositePrimaryKey.setIdInformazione(idInformazione);
	}

	public BigDecimal getIdInformazione()
	{
		return this.compositePrimaryKey.getIdInformazione();
	}

	public void setIdTipoInformazione(BigDecimal idTipoInformazione)
	{
		this.compositePrimaryKey.setIdTipoInformazione(idTipoInformazione);
	}

	public BigDecimal getIdTipoInformazione()
	{
		return this.compositePrimaryKey.getIdTipoInformazione();
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------

	public String getImageString()
	{
		try
		{
			if (immagine != null && immagine.length > 0)
			{
				byte[] encodeBase64 = Base64.encodeBase64(immagine);
				return new String(encodeBase64, "UTF-8");
			}
		}
		catch (Exception err)
		{
			err.printStackTrace();
		}
		return null;
	}

	public String getTitolo()
	{
		if (titolo != null)
		{
			String s = Jsoup.parse(titolo).text();
			return HtmlUtils.htmlEscape(s);
		}

		return titolo;
	}

	public void setTitolo(String titolo)
	{
		this.titolo = titolo;
	}

	public byte[] getImmagine()
	{
		return immagine;
	}

	public void setImmagine(byte[] immagine)
	{
		this.immagine = immagine;
	}

	public String getRicerca()
	{
		return ricerca;
	}

	public void setRicerca(String ricerca)
	{
		this.ricerca = ricerca;
	}

	public BigDecimal getStato()
	{
		return stato;
	}

	public void setStato(BigDecimal stato)
	{
		this.stato = stato;
	}

	public String getPrima()
	{
		if (prima != null)
		{
			String s = Jsoup.parse(prima).text();
			return HtmlUtils.htmlEscape(s);
		}

		return prima;
	}

	public void setPrima(String prima)
	{
		this.prima = prima;
	}

	public String getSeconda()
	{
		if (seconda != null)
		{
			String s = Jsoup.parse(seconda).text();
			return HtmlUtils.htmlEscape(s);
		}

		return seconda;
	}

	public void setSeconda(String seconda)
	{
		this.seconda = seconda;
	}

	public String getTerza()
	{
		if (terza != null)
		{
			String s = Jsoup.parse(terza).text();
			return HtmlUtils.htmlEscape(s);
		}

		return terza;
	}

	public void setTerza(String terza)
	{
		this.terza = terza;
	}

	public Date getDataAggiornamento()
	{
		return dataAggiornamento;
	}

	public void setDataAggiornamento(Date dataAggiornamento)
	{
		this.dataAggiornamento = dataAggiornamento;
	}

	public BigDecimal getNumeroVisite()
	{
		return numeroVisite;
	}

	public void setNumeroVisite(BigDecimal numeroVisite)
	{
		this.numeroVisite = numeroVisite;
	}

	public Date getDataUltimaVisita()
	{
		return dataUltimaVisita;
	}

	public void setDataUltimaVisita(Date dataUltimaVisita)
	{
		this.dataUltimaVisita = dataUltimaVisita;
	}

	public boolean isPubblicato()
	{
		return pubblicato;
	}

	public void setPubblicato(boolean pubblicato)
	{
		this.pubblicato = pubblicato;
	}

	public Date getScadenza()
	{
		return scadenza;
	}

	public void setScadenza(Date scadenza)
	{
		this.scadenza = scadenza;
	}

	public boolean isScaduto()
	{
		return (scadenza != null && scadenza.before(new Date()));
	}

	public BigDecimal getSortScadenza()
	{
		return sortScadenza;
	}

	public void setSortScadenza(BigDecimal sortScadenza)
	{
		this.sortScadenza = sortScadenza;
	}
	
	

	public Date getDataCancellazione()
	{
		return dataCancellazione;
	}

	public void setDataCancellazione(Date dataCancellazione)
	{
		this.dataCancellazione = dataCancellazione;
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (compositePrimaryKey != null)
		{
			sb.append(compositePrimaryKey.toString());
		}
		else
		{
			sb.append("(null-key)");
		}
		sb.append("]:");
		sb.append(titolo);
		sb.append("|");
		sb.append(prima);
		sb.append("|");
		sb.append(seconda);
		sb.append("|");
		sb.append(terza);
		sb.append("|");
		sb.append(immagine);
		sb.append("|");
		sb.append(ricerca);
		return sb.toString();
	}

}
