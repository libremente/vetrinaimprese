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
@Table(name = "PLF_T_RUOLO")
// Define named queries here
@NamedQueries({ @NamedQuery(name = "PLFTRuoloEntity.countAll", query = "SELECT COUNT(x) FROM PLFTRuoloEntity x") })
public class PLFTRuoloEntity implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@Column(name = "ID_RUOLO", nullable = false)
	private BigDecimal idRuolo;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------
	@Column(name = "COD_RUOLO", nullable = false, length = 200)
	private String codRuolo;

	@Column(name = "DESC_RUOLO", nullable = false, length = 200)
	private String descRuolo;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CREAZIONE")
	private Date dataCreazione;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_CANCELLAZIONE")
	private Date dataCancellazione;

	// ----------------------------------------------------------------------
	// ENTITY LINKS ( RELATIONSHIP )
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFTRuoloEntity()
	{
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------
	public BigDecimal getIdRuolo()
	{
		return idRuolo;
	}

	public void setIdRuolo(BigDecimal idRuolo)
	{
		this.idRuolo = idRuolo;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------

	public String getCodRuolo()
	{
		return codRuolo;
	}

	public void setCodRuolo(String codRuolo)
	{
		this.codRuolo = codRuolo;
	}

	public String getDescRuolo()
	{
		return descRuolo;
	}

	public void setDescRuolo(String descRuolo)
	{
		this.descRuolo = descRuolo;
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
//
//	public String toString()
//	{
//		StringBuffer sb = new StringBuffer();
//		sb.append("[");
//		sb.append(idRuolo);
//		sb.append("]:");
//		sb.append(codRuolo);
//		sb.append("|");
//		sb.append(descRuolo);
//		sb.append("|");
//		sb.append(dataCreazione);
//		sb.append("|");
//		sb.append("|");
//		sb.append(dataCancellazione);
//		return sb.toString();
//	}


	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR LINKS
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------


}
