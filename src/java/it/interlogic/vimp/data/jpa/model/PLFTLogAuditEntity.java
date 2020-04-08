package it.interlogic.vimp.data.jpa.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "PLF_T_LOG_AUDIT")
// Define named queries here
@NamedQueries({ @NamedQuery(name = "PLFTLogAuditEntity.countAll", query = "SELECT COUNT(x) FROM PLFTLogAuditEntity x") })
public class PLFTLogAuditEntity implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_LOG_AUDIT", nullable = false)
	private BigDecimal idLogAudit;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_ORA")
	private Date dataOra;

	@Column(name = "IP_ADDRESS", nullable = false, length = 40)
	private String ipAddress;

	@Column(name = "OPERAZIONE", nullable = false, length = 50)
	private String operazione;

	@Column(name = "OGGETTO_OPERAZIONE", nullable = false, length = 500)
	private String oggettoOperazione;

	@Column(name = "ID_UTENTE", nullable = false)
	private BigDecimal idUtente;

	// ----------------------------------------------------------------------
	// ENTITY LINKS ( RELATIONSHIP )
	// ----------------------------------------------------------------------

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFTLogAuditEntity()
	{
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------
	public BigDecimal getIdLogAudit()
	{
		return idLogAudit;
	}

	public void setIdLogAudit(BigDecimal idLogAudit)
	{
		this.idLogAudit = idLogAudit;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------

	public Date getDataOra()
	{
		return dataOra;
	}

	public void setDataOra(Date dataOra)
	{
		this.dataOra = dataOra;
	}

	public String getIpAddress()
	{
		return ipAddress;
	}

	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}

	public String getOperazione()
	{
		return operazione;
	}

	public void setOperazione(String operazione)
	{
		this.operazione = operazione;
	}

	public String getOggettoOperazione()
	{
		return oggettoOperazione;
	}

	public void setOggettoOperazione(String oggettoOperazione)
	{
		this.oggettoOperazione = oggettoOperazione;
	}

	public void setIdUtente(BigDecimal idUtente)
	{
		this.idUtente = idUtente;
	}

	public BigDecimal getIdUtente()
	{
		return idUtente;
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(idLogAudit);
		sb.append("]:");
		sb.append(dataOra);
		sb.append("|");
		sb.append(ipAddress);
		sb.append("|");
		sb.append(operazione);
		sb.append("|");
		sb.append(oggettoOperazione);
		sb.append("|");
		sb.append(idUtente != null ? idUtente.toString() : "");
		return sb.toString();
	}

}
