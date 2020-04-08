package it.interlogic.vimp.data.jpa.model.relation;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PLFROpportunitaStatoImpresaEntityKey implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------

	@Column(name = "ID_PLF_OPPORTUNITA", nullable = false)
	private BigDecimal idOpportunita;

	@Column(name = "ID_STATO_IMPRESA", nullable = false)
	private BigDecimal idStatoImpresa;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFROpportunitaStatoImpresaEntityKey()
	{
		super();
	}

	public PLFROpportunitaStatoImpresaEntityKey(BigDecimal idOpportunita, BigDecimal idStatoImpresa)
	{
		super();
		this.idOpportunita = idOpportunita;
		this.idStatoImpresa = idStatoImpresa;
	}

	public BigDecimal getIdOpportunita()
	{
		return idOpportunita;
	}

	public void setIdOpportunita(BigDecimal idOpportunita)
	{
		this.idOpportunita = idOpportunita;
	}

	public BigDecimal getIdStatoImpresa()
	{
		return idStatoImpresa;
	}

	public void setIdStatoImpresa(BigDecimal idStatoImpresa)
	{
		this.idStatoImpresa = idStatoImpresa;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idOpportunita == null) ? 0 : idOpportunita.hashCode());
		result = prime * result + ((idStatoImpresa == null) ? 0 : idStatoImpresa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PLFROpportunitaStatoImpresaEntityKey other = (PLFROpportunitaStatoImpresaEntityKey) obj;
		if (idOpportunita == null)
		{
			if (other.idOpportunita != null)
				return false;
		}
		else if (!idOpportunita.equals(other.idOpportunita))
			return false;
		if (idStatoImpresa == null)
		{
			if (other.idStatoImpresa != null)
				return false;
		}
		else if (!idStatoImpresa.equals(other.idStatoImpresa))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "PLFROpportunitaStatoImpresaEntityKey [idOpportunita=" + idOpportunita + ", idStatoImpresa=" + idStatoImpresa + "]";
	}

}
