package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the PLF_T_AREE_COMPETENZA database table.
 * 
 */
@Entity
@Table(name = "PLF_T_AREE_COMPETENZA")
@NamedQuery(name = "PLFTAreeCompetenzaEntity.findAll", query = "SELECT p FROM PLFTAreeCompetenzaEntity p")
public class PLFTAreeCompetenzaEntity implements Serializable, TranslatableCodifica
{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private BigDecimal id;

	@Column(name = "CODICE")
	private String codice;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_FINE")
	private Date dataFine;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_INIZIO")
	private Date dataInizio;

	@Column(name = "DESCRIZIONE")
	private String descrizione;

	public PLFTAreeCompetenzaEntity()
	{
	}

	/**********************************/
	@Override
	public BigDecimal getId()
	{
		return this.id;
	}

	@Override
	public void setId(BigDecimal id)
	{
		this.id = id;
		
	}
	
	/******************************************/
	@Override
	public String getCodice()
	{
		return this.codice;
	}

	@Override
	public void setCodice(String codice)
	{
		this.codice = codice;
		
	}
	/****************************************/

	public Date getDataFine()
	{
		return this.dataFine;
	}

	public void setDataFine(Date dataFine)
	{
		this.dataFine = dataFine;
	}

	public Date getDataInizio()
	{
		return this.dataInizio;
	}

	public void setDataInizio(Date dataInizio)
	{
		this.dataInizio = dataInizio;
	}

	public String getDescrizione()
	{
		return this.descrizione;
	}

	public void setDescrizione(String descrizione)
	{
		this.descrizione = descrizione;
	}

}