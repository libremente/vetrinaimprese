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
 * The persistent class for the PLF_LOG database table.
 * 
 */
@Entity
@Table(name = "PLF_LOG")
@NamedQuery(name = "PLFLogEntity.findAll", query = "SELECT p FROM PLFLogEntity p")
public class PLFLogEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_LOG")
	private BigDecimal idLog;

	@Column(name = "CODICE_FISCALE")
	private String codiceFiscale;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_ULTIMO_AGG_CCIA_ANAGRAFE")
	private Date dataUltimoAggCciaAnagrafe;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_ULTIMO_AGG_CCIA_REGISTRO")
	private Date dataUltimoAggCciaRegistro;

	@Column(name = "PARTITA_IVA")
	private String partitaIva;

	@Column(name = "RAGIONE_SOCIALE")
	private String ragioneSociale;

	// bi-directional many-to-one association to PLFImpresa
	@ManyToOne
	@JoinColumn(name = "ID_PLF_IMPRESA")
	private PLFImpresaEntity plfImpresa;

	@ManyToOne
	@JoinColumn(name = "ID_MESSAGGIO")
	private PLFTLogMessaggiEntity logMessaggi;

	public PLFLogEntity()
	{
	}

	public BigDecimal getIdLog()
	{
		return this.idLog;
	}

	public void setIdLog(BigDecimal idLog)
	{
		this.idLog = idLog;
	}

	public String getCodiceFiscale()
	{
		return this.codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale)
	{
		this.codiceFiscale = codiceFiscale;
	}

	public Date getDataUltimoAggCciaAnagrafe()
	{
		return this.dataUltimoAggCciaAnagrafe;
	}

	public void setDataUltimoAggCciaAnagrafe(Date dataUltimoAggCciaAnagrafe)
	{
		this.dataUltimoAggCciaAnagrafe = dataUltimoAggCciaAnagrafe;
	}

	public Date getDataUltimoAggCciaRegistro()
	{
		return this.dataUltimoAggCciaRegistro;
	}

	public void setDataUltimoAggCciaRegistro(Date dataUltimoAggCciaRegistro)
	{
		this.dataUltimoAggCciaRegistro = dataUltimoAggCciaRegistro;
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

	public PLFTLogMessaggiEntity getLogMessaggi()
	{
		return logMessaggi;
	}

	public void setLogMessaggi(PLFTLogMessaggiEntity logMessaggi)
	{
		this.logMessaggi = logMessaggi;
	}

}