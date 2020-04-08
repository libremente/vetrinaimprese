package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PLF_V_UTENTE")
// Define named queries here
@NamedQueries({ @NamedQuery(name = "PLFVUtenteEntity.countAll", query = "SELECT COUNT(x) FROM PLFVUtenteEntity x where x.dataCancellazione is null") })
public class PLFVUtenteEntity implements Serializable
{

	private static final long serialVersionUID = 1L;
	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@Column(name = "ID_UTENTE", nullable = false)
	private BigDecimal idUtente;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------

	@Column(name = "NOME", nullable = false, length = 200)
	private String nome;

	@Column(name = "COGNOME", nullable = false, length = 200)
	private String cognome;

	@Column(name = "EMAIL", nullable = false, length = 200)
	private String email;

	@Column(name = "CODICE_FISCALE", nullable = false, length = 16)
	private String codiceFiscale;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CREAZIONE")
	private Date dataCreazione;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CANCELLAZIONE")
	private Date dataCancellazione;

	@Column(name = "RICERCA")
	private String ricerca;

	@Column(name = "ID_IMPRESA")
	private BigDecimal idImpresa;

	@Column(name = "DESC_IMPRESA")
	private String descImpresa;

	@Column(name = "COD_FISCALE")
	private String codFiscale;

	@Column(name = "PARTITA_IVA")
	private String partitaIva;

	// ----------------------------------------------------------------------
	// ENTITY LINKS ( RELATIONSHIP )
	// ----------------------------------------------------------------------

	public void setIdRuolo(BigDecimal idRuolo)
	{
		ruolo.setIdRuolo(idRuolo);
	}

	@ManyToOne
	@JoinColumn(name = "ID_RUOLO", referencedColumnName = "ID_RUOLO")
	private PLFTRuoloEntity ruolo;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFVUtenteEntity()
	{
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------
	public BigDecimal getIdUtente()
	{
		return idUtente;
	}

	public void setIdUtente(BigDecimal idUtente)
	{
		this.idUtente = idUtente;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getCognome()
	{
		return cognome;
	}

	public void setCognome(String cognome)
	{
		this.cognome = cognome;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getCodiceFiscale()
	{
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale)
	{
		this.codiceFiscale = codiceFiscale;
	}

	public Date getDataCreazione()
	{
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione)
	{
		this.dataCreazione = dataCreazione;
	}

	public Date getDataCancellazione()
	{
		return dataCancellazione;
	}

	public void setDataCancellazione(Date dataCancellazione)
	{
		this.dataCancellazione = dataCancellazione;
	}

	public String getRicerca()
	{
		return ricerca;
	}

	public void setRicerca(String ricerca)
	{
		this.ricerca = ricerca;
	}

	public BigDecimal getIdImpresa()
	{
		return idImpresa;
	}

	public void setIdImpresa(BigDecimal idImpresa)
	{
		this.idImpresa = idImpresa;
	}

	public String getDescImpresa()
	{
		return descImpresa;
	}

	public void setDescImpresa(String descImpresa)
	{
		this.descImpresa = descImpresa;
	}

	public String getCodFiscale()
	{
		return codFiscale;
	}

	public void setCodFiscale(String codFiscale)
	{
		this.codFiscale = codFiscale;
	}

	public String getPartitaIva()
	{
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva)
	{
		this.partitaIva = partitaIva;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR LINKS
	// ----------------------------------------------------------------------
	public PLFTRuoloEntity getRuolo()
	{
		return ruolo;
	}

	public void setRuolo(PLFTRuoloEntity ruolo)
	{
		this.ruolo = ruolo;
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(idUtente);
		sb.append("]:");
		sb.append(nome);
		sb.append("|");
		sb.append(cognome);
		sb.append("|");
		sb.append(email);
		sb.append("|");
		sb.append(codiceFiscale);
		sb.append("|");
		sb.append(dataCreazione);
		sb.append("|");
		sb.append(dataCancellazione);
		sb.append("|");
		sb.append(ruolo != null ? ruolo.toString() : "");
		return sb.toString();
	}

}
