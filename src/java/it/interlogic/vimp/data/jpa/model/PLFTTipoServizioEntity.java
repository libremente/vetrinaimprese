package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PLF_T_TIPO_SERVIZIO")
// Define named queries here
@NamedQueries({ @NamedQuery(name = "PLFTTipoServizioEntity.countAll", query = "SELECT COUNT(x) FROM PLFTTipoServizioEntity x") })
public class PLFTTipoServizioEntity implements Serializable, TranslatableCodifica
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@Column(name = "ID", nullable = false)
	private BigDecimal id;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------

	@Column(name = "CODICE", nullable = false, length = 10)
	private String codice;

	@Column(name = "DESCRIZIONE", nullable = false, length = 255)
	private String descrizione;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_INIZIO", nullable = false)
	private Date dataInizio;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_FINE")
	private Date dataFine;
	


	// ----------------------------------------------------------------------
	// ENTITY LINKS ( RELATIONSHIP )
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFTTipoServizioEntity()
	{
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------

	@Override
	public String getCodice()
	{
		return codice;
	}

	@Override
	public BigDecimal getId()
	{
		return id;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------


//	public void setIdTipoServizio(BigDecimal idTipoServizio)
//	{
//		this.idTipoServizio = idTipoServizio;
//	}

//	public void setCodTipoServizio(String codTipoServizio)
//	{
//		this.codTipoServizio = codTipoServizio;
//	}
	
	@Override
	public void setId(BigDecimal id)
	{
		this.id = id;
	}

	@Override
	public void setCodice(String codice)
	{
		this.codice = codice;
	}

	public String getDescrizione()
	{
		return descrizione;
	}

	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
	}

	public Date getDataInizio()
	{
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio)
	{
		this.dataInizio = dataInizio;
	}

	public Date getDataFine()
	{
		return dataFine;
	}

	public void setDataFine(Date dataFine)
	{
		this.dataFine = dataFine;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR LINKS
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------

	@Override
	public String toString()
	{
		return "PLFTTipoNewsEntity [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", dataInizio=" + dataInizio + ", dataFine="
				+ dataFine + "]";
	}

}
