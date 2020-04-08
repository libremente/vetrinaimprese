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

@Entity
@Table(name = "PLF_V_RICHIESTA_ACCREDITAMENTO")
@NamedQuery(name = "PLFVRichiestaAccreditamentoEntity.findAll", query = "SELECT p FROM PLFVRichiestaAccreditamentoEntity p")
public class PLFVRichiestaAccreditamentoEntity implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_RICHIESTA_ACCREDITAMENTO")
	private BigDecimal idRichiestaAccreditamento;

	@Column(name = "TESTO")
	private String testo;

	@Column(name = "RAGIONE_SOCIALE")
	private String ragioneSociale;

	@Column(name = "DESC_IMPRESA")
	private String descImpresa;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA_RICHIESTA")
	private Date dataRichiesta;

	@Column(name = "ID_STATO_IMPRESA")
	private BigDecimal idStatoImpresa;

	@Column(name = "DESC_STATO_IMPRESA")
	private String descStatoImpresa;

	@Column(name = "ID_CONTROLLI_RICHIESTA")
	private BigDecimal idControlliRichiesta;

	@Column(name = "DESC_CONTROLLI_RICHIESTA")
	private String descControlliRichiesta;

	@Column(name = "ID_STATO_RICHIESTA")
	private BigDecimal idStatoRichiesta;

	@Column(name = "DESC_STATO_RICHIESTA")
	private String descStatoRichiesta;

	public PLFVRichiestaAccreditamentoEntity()
	{
	}

	public BigDecimal getIdRichiestaAccreditamento()
	{
		return idRichiestaAccreditamento;
	}

	public void setIdRichiestaAccreditamento(BigDecimal idRichiestaAccreditamento)
	{
		this.idRichiestaAccreditamento = idRichiestaAccreditamento;
	}

	public String getTesto()
	{
		return testo;
	}

	public void setTesto(String testo)
	{
		this.testo = testo;
	}

	public String getRagioneSociale()
	{
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale)
	{
		this.ragioneSociale = ragioneSociale;
	}

	public String getDescImpresa()
	{
		return descImpresa;
	}

	public void setDescImpresa(String descImpresa)
	{
		this.descImpresa = descImpresa;
	}

	public Date getDataRichiesta()
	{
		return dataRichiesta;
	}

	public void setDataRichiesta(Date dataRichiesta)
	{
		this.dataRichiesta = dataRichiesta;
	}

	public BigDecimal getIdStatoImpresa()
	{
		return idStatoImpresa;
	}

	public void setIdStatoImpresa(BigDecimal idStatoImpresa)
	{
		this.idStatoImpresa = idStatoImpresa;
	}

	public String getDescStatoImpresa()
	{
		return descStatoImpresa;
	}

	public void setDescStatoImpresa(String descStatoImpresa)
	{
		this.descStatoImpresa = descStatoImpresa;
	}

	public BigDecimal getIdControlliRichiesta()
	{
		return idControlliRichiesta;
	}

	public void setIdControlliRichiesta(BigDecimal idControlliRichiesta)
	{
		this.idControlliRichiesta = idControlliRichiesta;
	}

	public String getDescControlliRichiesta()
	{
		return descControlliRichiesta;
	}

	public void setDescControlliRichiesta(String descControlliRichiesta)
	{
		this.descControlliRichiesta = descControlliRichiesta;
	}

	public BigDecimal getIdStatoRichiesta()
	{
		return idStatoRichiesta;
	}

	public void setIdStatoRichiesta(BigDecimal idStatoRichiesta)
	{
		this.idStatoRichiesta = idStatoRichiesta;
	}

	public String getDescStatoRichiesta()
	{
		return descStatoRichiesta;
	}

	public void setDescStatoRichiesta(String descStatoRichiesta)
	{
		this.descStatoRichiesta = descStatoRichiesta;
	}

}