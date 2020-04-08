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
@Table(name = "PLF_T_COMUNE")
// Define named queries here
@NamedQueries({ @NamedQuery(name = "PLFTComuneEntity.countAll", query = "SELECT COUNT(x) FROM PLFTComuneEntity x") })
public class PLFTComuneEntity implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@Column(name = "ID_PLF_T_COMUNE", nullable = false)
	private BigDecimal idComune;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------

	@Column(name = "COD_COMUNE", nullable = false, length = 4)
	private String codComune;

	@Column(name = "DESC_COMUNE", nullable = false, length = 100)
	private String descComune;

	@Column(name = "COD_ISTAT", nullable = false, length = 6)
	private String codiceIstat;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_INI_COMUNE", nullable = false)
	private Date dataIniComune;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_FINE_COMUNE")
	private Date dataFineComune;

	// ----------------------------------------------------------------------
	// ENTITY LINKS ( RELATIONSHIP )
	// ----------------------------------------------------------------------

	@ManyToOne
	@JoinColumn(name = "COD_PROVINCIA", referencedColumnName = "COD_PROVINCIA", insertable = false, updatable = false)
	private PLFTProvinciaEntity provincia;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFTComuneEntity()
	{
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------

	public BigDecimal getIdComune()
	{
		return idComune;
	}

	public void setIdComune(BigDecimal idComune)
	{
		this.idComune = idComune;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------

	public String setCodComune()
	{
		return codComune;
	}

	public void setCodComune(String codComune)
	{
		this.codComune = codComune;
	}

	public String getDescComune()
	{
		return descComune;
	}

	public void setDescComune(String descComune)
	{
		this.descComune = descComune;
	}

	public Date getDataIniComune()
	{
		return dataIniComune;
	}

	public void setDataIniComune(Date dataIniComune)
	{
		this.dataIniComune = dataIniComune;
	}

	public Date getDataFineComune()
	{
		return dataFineComune;
	}

	public void setDataFineComune(Date dataFineComune)
	{
		this.dataFineComune = dataFineComune;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR LINKS
	// ----------------------------------------------------------------------

	public String getCodiceIstat()
	{
		return codiceIstat;
	}

	public void setCodiceIstat(String codiceIstat)
	{
		this.codiceIstat = codiceIstat;
	}

	public String getCodComune()
	{
		return codComune;
	}

	public PLFTProvinciaEntity getProvincia()
	{
		return provincia;
	}

	public void setProvincia(PLFTProvinciaEntity provincia)
	{
		this.provincia = provincia;
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(idComune);
		sb.append("]:");
		sb.append(codComune);
		sb.append("|");
		sb.append(descComune);
		sb.append("|");
		sb.append(dataIniComune);
		sb.append("|");
		sb.append(dataFineComune);
		sb.append("|");
		sb.append(provincia != null ? provincia.toString() : "");
		return sb.toString();
	}

}
