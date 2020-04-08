package it.interlogic.vimp.data.jpa.model.relation;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PLFRImpresaMercatiEntityKey implements Serializable
{

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------

	@Column(name = "ID_MERCATI", nullable = false)
	private BigDecimal idMercato;

	@Column(name = "ID_PLF_IMPRESA", nullable = false)
	private BigDecimal idImpresa;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public PLFRImpresaMercatiEntityKey()
	{
		super();
	}

	public PLFRImpresaMercatiEntityKey(BigDecimal idImpresa, BigDecimal idMercato)
	{
		super();
		this.idImpresa = idImpresa;
		this.idMercato = idMercato;
	}

	public BigDecimal getIdMercato()
	{
		return idMercato;
	}

	public void setIdMercato(BigDecimal idMercato)
	{
		this.idMercato = idMercato;
	}

	public BigDecimal getIdImpresa()
	{
		return idImpresa;
	}

	public void setIdImpresa(BigDecimal idImpresa)
	{
		this.idImpresa = idImpresa;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idImpresa == null) ? 0 : idImpresa.hashCode());
		result = prime * result + ((idMercato == null) ? 0 : idMercato.hashCode());
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
		PLFRImpresaMercatiEntityKey other = (PLFRImpresaMercatiEntityKey) obj;
		if (idImpresa == null)
		{
			if (other.idImpresa != null)
				return false;
		}
		else if (!idImpresa.equals(other.idImpresa))
			return false;
		if (idMercato == null)
		{
			if (other.idMercato != null)
				return false;
		}
		else if (!idMercato.equals(other.idMercato))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "PLFRImpresaMercatiEntityKey [idMercato=" + idMercato + ", idImpresa=" + idImpresa + "]";
	}

}
