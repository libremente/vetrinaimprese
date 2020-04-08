package it.interlogic.vimp.data.jpa.model;

import it.interlogic.vimp.domain.UtenteDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "PLF_UTENTE")
// Define named queries here
@NamedQueries({ @NamedQuery(name = "PLFTUtenteEntity.countAll", query = "SELECT COUNT(x) FROM PLFTUtenteEntity x") })
public class PLFTUtenteEntity implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_UTENTE", nullable = false)
	private BigDecimal idUtente;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------

	@Column(name = "NOME", nullable = false, length = 200)
	private String nome;

	@Column(name = "COGNOME", nullable = false, length = 200)
	private String cognome;

	@Column(name = "CODICE_FISCALE", nullable = false, length = 16)
	private String codiceFiscale;

	@Column(name = "EMAIL", nullable = false, length = 200)
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CREAZIONE")
	private Date dataCreazione;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CANCELLAZIONE")
	private Date dataCancellazione;

	@Transient
	private List<PLFImpresaEntity> plfImpresas;

	// ----------------------------------------------------------------------
	// UTILITY DATA FIELDS
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// ENTITY LINKS ( RELATIONSHIP )
	// ----------------------------------------------------------------------

	public void setIdRuolo(BigDecimal idRuolo)
	{
		if (ruolo == null)
			ruolo = new PLFTRuoloEntity();
		ruolo.setIdRuolo(idRuolo);
	}

	@ManyToOne
	@JoinColumn(name = "ID_RUOLO", referencedColumnName = "ID_RUOLO")
	private PLFTRuoloEntity ruolo;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFTUtenteEntity()
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

	public Date getDataCreazione()
	{
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione)
	{
		this.dataCreazione = dataCreazione;
	}

	public String getCodiceFiscale()
	{
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale)
	{
		this.codiceFiscale = codiceFiscale;
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
	// GETTERS & SETTERS FOR UTILITY
	// ----------------------------------------------------------------------

	public List<PLFImpresaEntity> getPlfImpresas()
	{
		return this.plfImpresas;
	}

	public void setPlfImpresas(List<PLFImpresaEntity> plfImpresas)
	{
		this.plfImpresas = plfImpresas;
	}

	public boolean isAdministrator()
	{
		return (ruolo != null && ruolo.getIdRuolo() != null && ruolo.getIdRuolo().intValue() == UtenteDto.RUOLO_ADMIN);
	}

	public boolean isImpresa()
	{
		return (ruolo != null && ruolo.getIdRuolo() != null && ruolo.getIdRuolo().intValue() == UtenteDto.RUOLO_IMPRESA);
	}

	public boolean isStakeholder()
	{
		return (ruolo != null && ruolo.getIdRuolo() != null && ruolo.getIdRuolo().intValue() == UtenteDto.RUOLO_STAKEHOLDER);
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
		sb.append(codiceFiscale);
		sb.append("|");
		sb.append(email);
		sb.append("|");
		sb.append(dataCreazione);
		sb.append("|");
		sb.append(dataCancellazione);
		sb.append("|");
		sb.append(ruolo != null ? ruolo.toString() : "");
		return sb.toString();
	}

}
